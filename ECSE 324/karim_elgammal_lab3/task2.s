.global _start
.equ pixel_buffer_address, 0xC8000000
.equ char_buffer_address, 0xC9000000
.equ ps2_data_register, 0xFF200100
_start:
        bl      input_loop
end:
        b       end
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@This function takes in r0, and r1 as coordinates
@Further takes r2 as the asccii code input 
@The function takes the coordinates and writes to the character buffer the 
@value of the ascii code 
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
VGA_write_char_ASM: 
		@compare before we store
		@store if it is valid input
		PUSH {r0-r3, LR}
		CMP r0, #79
		BGT char_write_end	
		CMP r0, #0
		BLT char_write_end
		CMP r1, #59 
		BGT char_write_end
		CMP r1, #0
		BLT char_write_end	
		LDR r3, =char_buffer_address 
		LSL r1, #7
		ADD r3, r3, r1
		ADD r3, r3, r0
		STRB r2, [r3] 
		B char_write_end		
char_write_end: 
		POP {r0 - r3, LR}
		BX LR
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@This function takes in no arguments 
@Asserts an ASSCII code of 0, to clear the character buffer 
@then iterates through each character coordinate to clear the characters 
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
VGA_clear_charbuff_ASM:
		PUSH {r0, r1, r2, LR}
		MOV r0, #0 @our x coordinate
		MOV r1, #0 @ our y coordinate
		MOV r2, #0 @ our asscii code 0
		BL choose_x_char_clearing
		POP {r0, r1, r2, LR}
		BX LR				
choose_x_char_clearing: 
		CMP r0, #79
		@LDRGT LR, [SP], #4
		BXGT LR
		B choose_y_char_clearing
choose_x2_char_clearing: 
		POP { LR } 
		ADD r0, r0, #1 
		MOV r1, #0
		B choose_x_char_clearing
choose_y_char_clearing:
		PUSH {LR}
		CMP r1, #59 
		BGT choose_x2_char_clearing
		BL VGA_write_char_ASM
		POP {LR}
		ADD r1, r1, #1
		B choose_y_char_clearing
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@This function takes in r0, and r1 as coordinates
@Further takes r2 as the RGB color code input 
@The function takes the coordinates and writes to the pixel buffer the 
@value of the RGB color  code 
@we want to take the x and y coordinates given shift , then concatenate them 
@then we want to use that concanted value to store the color into the memory
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
VGA_draw_point_ASM: 
		@our x coordinate is in r0 and our y coordinate is in r1 and our color is in r2 
		push { r0-r3 } 
		@we want to take the x and y coordinates given shift , then concatenate them 
		@ then we want to use that concanted value to store the color into the memory 
		LSL r0, #1
		LSL r1, #10
		ADD r0, r0, r1
		@ our coordinates are now in r0, and we want to add that number to our base address 
		@ so we are loading the base address into r3 
		LDR r3, =pixel_buffer_address
		ADD r0, r0, r3
		@ our whole address is now ready so we can access it and store the color that we have 
		STRH r2, [r0]
		POP { r0-r3}
		BX LR
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@This function takes in no arguments 
@Asserts an rgb colour code of 0, to clear the pixel buffer and display a black screen
@then iterates through each character coordinate to clear the pixels 
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@	
VGA_clear_pixelbuff_ASM: 
		PUSH {r0, r1, r2, r3, LR}
		MOV r0, #0 @our x coordinate 
		MOV r1, #0 @our y coordinate
		MOV r2, #0 @our color
		MOVW r3, #319
		BL	choose_x_pixel_clearing
		POP {r0, r1, r2, r3, LR}
		BX LR
choose_x_pixel_clearing: 
		CMP r0, r3
		@LDRGT LR, [SP], #4
		BXGT LR
		B choose_y_pixel_clearing
choose_x2_pixel_clearing: 
		POP { LR } 
		ADD r0, r0, #1 
		MOV r1, #0
		B choose_x_pixel_clearing
choose_y_pixel_clearing:
		PUSH {LR}
		CMP r1, #239 
		BGT choose_x2_pixel_clearing
		BL VGA_draw_point_ASM
		POP {LR}
		ADD r1, r1, #1
		B choose_y_pixel_clearing
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@This function takes in r0 argument, which is an address in memory
@Further, the function returns (by way of r0) the RVALID bit in the ps/2 
@ data register
@The function is a simple access and exchange of pointers,
@lets us know if the data in the PS/2 register is ready to be read
@ if it is it places that data in the address fed by r0
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
read_PS2_data_ASM: 
	@r0 pointer argument is fed into this function we want to store the data there
	@r0 returns weather the integer is valid or not 
	PUSH {r1 - r3, LR}
	LDR r1, =ps2_data_register
	LDR r2, [r1] 
	MOV r3, r2
	AND r3, r3, #0xff
	LSR r2, #15
	AND r2, r2, #0x1
	TST r2, #0x1
	STRNEB r3, [r0]
	MOV r0, r2
	POP {r1 - r3, LR}
	BX LR	
write_hex_digit:
        push    {r4, lr}
        cmp     r2, #9
        addhi   r2, r2, #55
        addls   r2, r2, #48
        and     r2, r2, #255
        bl      VGA_write_char_ASM
        pop     {r4, pc}
write_byte:
        push    {r4, r5, r6, lr}
        mov     r5, r0
        mov     r6, r1
        mov     r4, r2
        lsr     r2, r2, #4
        bl      write_hex_digit
        and     r2, r4, #15
        mov     r1, r6
        add     r0, r5, #1
        bl      write_hex_digit
        pop     {r4, r5, r6, pc}
input_loop:
        push    {r4, r5, lr}
        sub     sp, sp, #12
        bl      VGA_clear_pixelbuff_ASM
        bl      VGA_clear_charbuff_ASM
        mov     r4, #0
        mov     r5, r4
        b       .input_loop_L9
.input_loop_L13:
        ldrb    r2, [sp, #7]
        mov     r1, r4
        mov     r0, r5
        bl      write_byte
        add     r5, r5, #3
        cmp     r5, #79
        addgt   r4, r4, #1
        movgt   r5, #0
.input_loop_L8:
        cmp     r4, #59
        bgt     .input_loop_L12
.input_loop_L9:
        add     r0, sp, #7
        bl      read_PS2_data_ASM
        cmp     r0, #0
        beq     .input_loop_L8
        b       .input_loop_L13
.input_loop_L12:
        add     sp, sp, #12
        pop     {r4, r5, pc}		