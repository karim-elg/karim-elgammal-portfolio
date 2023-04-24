.global _start
.equ pixel_buffer_address, 0xC8000000
.equ char_buffer_address, 0xC9000000
_start:
        bl      draw_test_screen
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
		CMP r1, #59 
		BGT char_write_end	
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

draw_test_screen:
        push    {r4, r5, r6, r7, r8, r9, r10, lr}
        bl      VGA_clear_pixelbuff_ASM
        bl      VGA_clear_charbuff_ASM
        mov     r6, #0
        ldr     r10, .draw_test_screen_L8
        ldr     r9, .draw_test_screen_L8+4
        ldr     r8, .draw_test_screen_L8+8
        b       .draw_test_screen_L2
.draw_test_screen_L7:
        add     r6, r6, #1
        cmp     r6, #320
        beq     .draw_test_screen_L4
.draw_test_screen_L2:
        smull   r3, r7, r10, r6
        asr     r3, r6, #31
        rsb     r7, r3, r7, asr #2
        lsl     r7, r7, #5
        lsl     r5, r6, #5
        mov     r4, #0
.draw_test_screen_L3:
        smull   r3, r2, r9, r5
        add     r3, r2, r5
        asr     r2, r5, #31
        rsb     r2, r2, r3, asr #9
        orr     r2, r7, r2, lsl #11
        lsl     r3, r4, #5
        smull   r0, r1, r8, r3
        add     r1, r1, r3
        asr     r3, r3, #31
        rsb     r3, r3, r1, asr #7
        orr     r2, r2, r3
        mov     r1, r4
        mov     r0, r6
        bl      VGA_draw_point_ASM
        add     r4, r4, #1
        add     r5, r5, #32
        cmp     r4, #240
        bne     .draw_test_screen_L3
        b       .draw_test_screen_L7
.draw_test_screen_L4:
        mov     r2, #72
        mov     r1, #5
        mov     r0, #20
        bl      VGA_write_char_ASM
        mov     r2, #101
        mov     r1, #5
        mov     r0, #21
        bl      VGA_write_char_ASM
        mov     r2, #108
        mov     r1, #5
        mov     r0, #22
        bl      VGA_write_char_ASM
        mov     r2, #108
        mov     r1, #5
        mov     r0, #23
        bl      VGA_write_char_ASM
        mov     r2, #111
        mov     r1, #5
        mov     r0, #24
        bl      VGA_write_char_ASM
        mov     r2, #32
        mov     r1, #5
        mov     r0, #25
        bl      VGA_write_char_ASM
        mov     r2, #87
        mov     r1, #5
        mov     r0, #26
        bl      VGA_write_char_ASM
        mov     r2, #111
        mov     r1, #5
        mov     r0, #27
        bl      VGA_write_char_ASM
        mov     r2, #114
        mov     r1, #5
        mov     r0, #28
        bl      VGA_write_char_ASM
        mov     r2, #108
        mov     r1, #5
        mov     r0, #29
        bl      VGA_write_char_ASM
        mov     r2, #100
        mov     r1, #5
        mov     r0, #30
        bl      VGA_write_char_ASM
        mov     r2, #33
        mov     r1, #5
        mov     r0, #31
        bl      VGA_write_char_ASM
        pop     {r4, r5, r6, r7, r8, r9, r10, pc}
.draw_test_screen_L8:
        .word   1717986919
        .word   -368140053
        .word   -2004318071	