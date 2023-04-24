.global _start
.equ pixel_buffer_address, 0xC8000000
.equ char_buffer_address, 0xC9000000
.equ ps2_data_register, 0xFF200100
.equ background_color, 0x11
.equ timer_load_register, 0xFFFEC600
.equ timer_control_register, 0xFFFEC608
.equ f_bit_register, 0xFFFEC60C
ps2_store: .word 0 
timer_config: .word 0x77359400
array_of_coordinates: .word 3, 10, 16, 23, 30, 36, 43, 49, 56, 62, 69, 75
input_mazes:// First Obstacle Course
            .word 2,1,0,1,1,1,0,0,0,1,0,1
            .word 0,1,0,1,1,1,0,0,0,1,0,1
            .word 0,1,0,0,0,0,0,0,0,1,0,1
            .word 0,1,0,1,1,1,0,0,0,1,1,1
            .word 0,1,0,1,1,1,0,0,0,1,1,1
            .word 0,0,0,1,1,1,0,0,0,1,1,1
            .word 1,1,1,1,1,1,0,0,1,0,0,0
            .word 1,1,1,1,1,1,0,1,0,0,0,0
            .word 1,1,1,1,1,1,0,0,0,0,0,3
            // Second Obstacle Course
            .word 2,1,0,1,1,1,0,0,0,0,0,1
            .word 0,1,0,1,1,1,0,0,0,1,0,1
            .word 0,1,0,0,0,0,0,0,0,1,0,1
            .word 0,1,0,1,1,1,0,0,0,1,0,1
            .word 0,1,0,1,1,1,0,0,0,1,0,1
            .word 0,0,0,1,1,1,0,0,0,1,0,1
            .word 1,1,1,1,1,1,0,0,1,0,0,0
            .word 1,1,1,1,1,1,0,1,0,0,0,0
            .word 1,1,1,1,1,1,1,0,0,0,0,3
            // Third Obstacle Course
            .word 2,0,0,0,0,1,0,0,0,1,0,1
            .word 0,1,1,1,0,1,1,1,0,1,0,1
            .word 0,1,0,0,0,0,0,0,0,0,0,1
            .word 0,1,1,1,1,1,0,1,1,1,0,1
            .word 0,1,0,0,0,0,0,0,0,1,0,1
            .word 1,1,0,1,1,1,1,1,1,1,1,1
            .word 0,1,0,0,0,0,0,0,0,0,0,1
            .word 0,1,1,1,0,1,1,1,1,1,0,1
            .word 0,0,0,0,0,0,0,1,0,0,0,3
            // Fourth Obstacle Course
            .word 2,1,0,0,0,0,0,0,0,0,0,1
            .word 0,1,0,1,1,1,0,1,1,1,0,1
            .word 0,1,0,0,0,1,0,1,0,1,0,1
            .word 0,1,0,1,0,1,1,1,0,1,0,1
            .word 0,0,0,1,0,0,0,0,0,1,0,1
            .word 0,1,0,1,1,1,1,1,1,1,0,1
            .word 0,1,0,1,0,0,0,1,0,0,0,1
            .word 0,1,0,1,1,1,0,1,0,1,1,1
            .word 0,1,0,1,0,0,0,0,0,0,0,3
            // Fifth Obstacle Course
            .word 2,0,0,0,0,1,0,1,0,1,0,1
            .word 1,1,0,1,1,1,0,1,0,1,0,1
            .word 0,0,0,0,0,0,0,0,0,0,0,1
            .word 0,1,1,1,0,1,1,1,1,1,0,1
            .word 0,0,0,1,0,1,0,1,0,0,0,1
            .word 1,1,0,1,1,1,0,1,1,1,0,1
            .word 0,0,0,1,0,1,0,0,0,0,0,1
            .word 0,1,0,1,0,1,0,1,0,1,1,1
            .word 0,1,0,0,0,1,0,1,0,0,0,3
            // Sixth Obstacle Course
            .word 2,0,0,0,0,0,0,1,0,0,0,1
            .word 1,1,0,1,0,1,0,1,0,1,0,1
            .word 0,0,0,1,0,1,0,0,0,1,0,1
            .word 1,1,1,1,0,1,1,1,1,1,1,1
            .word 0,0,0,1,0,0,0,1,0,0,0,1
            .word 0,1,1,1,0,1,1,1,0,1,0,1
            .word 0,1,0,0,0,0,0,0,0,1,0,1
            .word 0,1,0,1,1,1,1,1,1,1,0,1
            .word 0,0,0,0,0,0,0,0,0,1,0,3
            // Seventh Obstacle Course
            .word 2,0,0,0,0,0,0,0,1,0,1,0
            .word 1,1,1,0,1,1,1,1,1,0,1,0
            .word 1,0,0,0,0,0,1,0,0,0,0,0
            .word 1,1,1,1,1,0,1,1,1,0,1,1
            .word 1,0,0,0,1,0,0,0,0,0,0,0
            .word 1,0,1,0,1,0,1,0,1,0,1,0
            .word 1,0,1,0,0,0,1,0,1,0,1,0
            .word 1,1,1,1,1,1,1,1,1,0,1,0
            .word 1,0,0,0,0,0,0,0,0,0,1,3
            // Eighth Obstacle Course
            .word 2,0,0,0,0,0,0,0,0,0,0,0
            .word 1,0,1,1,1,1,1,0,1,1,1,0
            .word 1,0,0,0,1,0,0,0,1,0,0,0
            .word 1,1,1,1,1,0,1,1,1,1,1,1
            .word 1,0,0,0,1,0,1,0,0,0,0,0
            .word 1,0,1,1,1,0,1,1,1,0,1,0
            .word 1,0,0,0,0,0,0,0,1,0,1,0
            .word 1,1,1,0,1,0,1,1,1,1,1,0
            .word 1,0,0,0,1,0,0,0,0,0,0,3
            // Nineth Obstacle Course
            .word 2,0,0,0,0,0,1,0,1,0,1,0
            .word 1,0,1,1,1,1,1,0,1,0,1,0
            .word 1,0,0,0,1,0,0,0,0,0,0,0
            .word 1,0,1,0,1,1,1,0,1,1,1,1
            .word 1,0,1,0,1,0,1,0,0,0,1,0
            .word 1,0,1,1,1,0,1,0,1,1,1,0
            .word 1,0,0,0,1,0,0,0,0,0,1,0
            .word 1,0,1,1,1,0,1,1,1,0,1,0
            .word 1,0,0,0,0,0,1,0,0,0,0,3


end:
        b       end		
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@This function takes in r0, and r1 as coordinates
@Further takes r2 as the asccii code input 
@The function takes the coordinates and writes to the character buffer the 
@value of the ascii code 
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
VGA_write_char_ASM: 
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
		push { r0-r5, LR } 
		LSL r0, #1
		LSL r1, #10
		ADD r0, r0, r1
		LDR r3, =pixel_buffer_address
		ADD r0, r0, r3
		STRH r2, [r0]
		POP { r0-r5 , LR}
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
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@ This function takes no arguments and does not return any
@ This function is to fill the screen with a singular color 
@ it utilizes the helper functions we created for the pixel clearing function
@ We assert a color of blue for all the pixels
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
VGA_fill_ASM: 
		PUSH {r0, r1, r2, r3, r4, r5, LR}
		MOV r0, #0 
		MOV r1, #0 
		LDR r2, =background_color 
		MOVW r3, #319
		BL	choose_x_pixel_clearing
		POP {r0, r1, r2, r3, r4, r5, LR}
		BX LR
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@This function takes no inputs; and simply draws the empty 9 by 12 grid 
@This is done by utilizing helper subroutines that iterate through the valid
@coordinates of the screen and further subroutines that help us draw the horizontal
@ and vertical lines seperately
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
draw_grid_ASM:
		PUSH { r0-r5, LR}
		MOV r0, #2 @our left and right is bounded by 2 pixels, and top and bottom bounded by 1 pixel so we start from (2,1)
		MOV r1, #2 @ we start drawing from (2,1) 
		MOV r2, #0 @assume r2 carries our color 
		MOV r3, #0 @counter that allows us to draw 2 pixel at a time 
		MOV r4, #0 
		B line_driver		
line_driver: 
		CMP r1, #238
		BLLT VGA_draw_point_ASM
		BGT line_driver2
		ADD r1, r1, #1
		B line_driver	
line_driver2: 
		CMP r3, #1
		BEQ line_driver3
		ADD r0, r0, #1 @increment x
		ADD r3, r3, #1 @increment 2 pixel counter
		MOV r1, #2 @reset our y
		B line_driver
line_driver3:
		CMP r4, #12
		BEQ draw_grid_ASM2
		ADD r0, r0, #25
		ADD r4, r4, #1
		MOV r1, #2
		MOV r3, #0
		B line_driver	
draw_grid_ASM2: 
		MOV r0, #2
		MOV r1, #2
		MOV r2, #0
		MOV r3, #0
		MOV r4, #0
		MOVW r5, #316
		B h_line_driver
h_line_driver:
		CMP r0, r5
		BLLT VGA_draw_point_ASM
		BGT h_line_driver2
		ADD r0, r0, #1
		B h_line_driver
h_line_driver2:
		CMP r3, #1
		BEQ h_line_driver3
		ADD r1, r1, #1
		ADD r3, r3, #1 
		MOV r0, #2
		B h_line_driver	
h_line_driver3:
		CMP r4, #9
		BEQ end_draw_grid
		ADD r1, r1, #25
		ADD r4, r4, #1 
		MOV r0, #2
		MOV r3, #0
		B h_line_driver	
end_draw_grid:
		POP {r0-r5, lr} 
		BX LR	
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@This function take in r0 and r1 as our x and y coordinates in terms of the 
@ squares that we want to draw as blocks
@ we essentially utilized the same structure for filling the pixels  of entire screen 
@ by simple math, we were able to calculate the best fitting sizes on to our dimensions
@ and by these calulations we arrive at 26 by 26 pixel boxes
@ the function iterates through all valid values of the 'box' and fills them with black
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
draw_square_ASM: @r0 has our x coordinate, r1 has our y coordinate
		PUSH {r0-r5, LR}
		BL VGA_clear_charbuff_ASM 
		MOV r3, #26
		MOV r2, #0 @our color
		MUL r0, r0, r3 @our x coordinate 
		MUL r1, r1, r3 @our y coordinate
		ADD r0, r0, #2
		ADD r1, r1, #2
		MOV r4, #0 @relative counter so we dont need to keep track of x coordinate 
		MOV r5, #0 @relative counter so we dont need to keep track of y coordinate
		PUSH {r0}
		B draw_square_driver
draw_square_driver:
		CMP r4, #26
		BLLT VGA_draw_point_ASM
		BGT draw_square_driver2
		ADD r0, r0, #1
		ADD r4, r4, #1
		B draw_square_driver
draw_square_driver2:
	CMP r5, #25
	BEQ draw_square_end
	LDR r0, [sp] @ we need to get vlaue of r0 before we incremented
	ADD r1, r1, #1
	ADD r5, r5, #1
	MOV r4, #0
	B draw_square_driver
draw_square_end:
	POP {r0}
	POP {r0-r5, LR}
	BX LR
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@ This function takes in an argument of r0 and r1 for the x and y coordinates 
@ of where we want the ampersand to be printed
@ we feed into this function the coordinates in terms of the 9 by 12 grid
@ and then it uses a 'look up' array to find the corresponding location on screen 
@ in terms of character coordinates
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
draw_ampersand_ASM:
	PUSH {r0-r4, LR}
	MOV r4, #4
	LDR r3, =array_of_coordinates
	MUL r0, r0, r4
	MUL r1, r1, r4
	ADD r0, r0, r3
	ADD r1, r1, r3
	LDR r0, [r0]
	LDR r1, [r1]	
	MOV r2, #38 @our asscii code 
	BL VGA_write_char_ASM
	POP {r0-r4, LR}
	BX LR
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@ This function takes in no arguments as the exit is at a fixed location
@ The function then writes the characters to represent the exit, into the 
@ pre defined character coordinates
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
draw_exit_ASM:
	PUSH {r0 - r2, LR}
	MOV r0, #74
	MOV r1, #56
	MOV r2, #0x45
	BL VGA_write_char_ASM 
	MOV r0, #75
	MOV r2, #0x4e
	BL VGA_write_char_ASM
	MOV r0, #76
	MOV r2, #0x44
	BL VGA_write_char_ASM
	POP {r0-r2, LR}
	BX LR
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@This function takes from r0 the number of the map we would like to access
@ We then use relative addressing to access the starting address of the specific map 
@ we would like to access. We then iterate through the map in memory, while keeping track 
@ of the game play coordinates; that are then fed into the draw_square_ASM to draw each square 
@corresponding to 1 in memeory
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
fill_grid_ASM: @r0 has the value of which map to take on
	PUSH {r0 - r5, LR}
	SUB r0, #1
	LDR r1, =input_mazes
	MOVW r2, #432
	MUL r0, r2
	ADD r1, r1, r0 @our starting address is in r1
	MOV r4, #0 @our x coordinate 
	MOV r5, #0 @ our y coordinate
	B fill_grid_loop
fill_grid_loop:
	LDR r3, [r1], #4
	CMP r3, #0x1
	BLEQ fill_grid_driver
	CMP r3, #0x3
	BEQ end_fill_grid
	ADD r4, r4, #1 
	CMP r4, #12
	MOVEQ r4, #0
	ADDEQ r5, #1
	B fill_grid_loop	
fill_grid_driver: 
	PUSH {r0, r1, LR} 
	MOV r0, r4
	MOV r1, r5
	BL draw_square_ASM
	POP {r0, r1, LR}
	BX LR	
end_fill_grid: 
	POP {r0 - r5, LR}
	BX LR 
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@This function takes as an input r0, r1, and r2, where r0 and r1 are our proposed
@ new coordinates for the ampersand to move in, and r2, the number of map we are playing in 
@ The function first checks if this move would be legal 
@ and then using relative addressing checks if there is a 'block' (1)
@ in the location we want to move to. If there is no block we simply move the ampersand
@ if there is a block the subroutine simply returns and returns a value in r3 which 
@ lets us know if a move has been executed or not 
@ further this function checks if we have moved to the end and starts the end screen subroutine
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
move_ASM: 
	@we have our new postion in r0, r1
	@and we have map number in r2
	PUSH {r0, r1, r2, r4, r5, r6, LR}
	CMP r0, #0 
	BLT end_of_move 
	CMP r1, #0 
	BLT end_of_move
	CMP r0, #11
	BGT end_of_move
	CMP r1, #8
	BGT end_of_move	
	SUB r2, #1
	LDR r3, =input_mazes
	MOVW r4, #432
	MUL r2, r4
	ADD r3, r3, r2
	@now our address of map is in r3 (our base)
	PUSH {r0, r1} 
	LSL r0, #2
	MOV r5, #48
	MUL r1, r5
	ADD r3, r3, r0
	ADD r3, r3, r1
	LDR r6, [r3]
	CMP r6, #0x1 
	POPEQ {r0, r1}
	MOV r3, #0
	BEQ end_of_move
	BNE make_a_move
make_a_move:
	POP {r0, r1} 
	BL VGA_clear_charbuff_ASM
	BL draw_exit_ASM
	BL draw_ampersand_ASM
	MOV r3, #1
	MOV r6, #0
	CMP r0, #11
	MOVEQ r6, #0x1
	CMP r1, #8
	ADDEQ r6, r6, #0x1
	CMP r6, #2
	POPEQ {r0, r1, r2, r4, r5, r6, LR}
	MOVEQ r0, #1
	BEQ result_ASM
	B end_of_move	
end_of_move:
	POP {r0, r1, r2, r4, r5, r6, LR}
	BX LR
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@ this subroutine takes an argument in r0 weather it is a win or lose
@ then displays the win screen 
@ after doing so, it branches to a timer that counts 10 seconds before restarting 
@ the whole game
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
result_ASM: 
	CMP r0, #0x1
	BEQ win_result
	BNE end_result
win_result: 
	BL VGA_fill_ASM
	BL VGA_clear_charbuff_ASM
	MOV r0, #32
	MOV r1, #30
	MOV r2, #89
	BL VGA_write_char_ASM
	MOV r0, #33
	MOV r2, #79
	BL VGA_write_char_ASM
	MOV r0, #34
	MOV r2, #85
    BL VGA_write_char_ASM
	MOV r0, #36
	MOV r2, #87
	BL VGA_write_char_ASM
	MOV r0, #37
	MOV r2, #73
	BL VGA_write_char_ASM
	MOV r0, #38
	MOV r2, #78
	BL VGA_write_char_ASM
	MOV r0, #39
	MOV r2, #33
	BL VGA_write_char_ASM
	MOV r0, #41
	MOV r2, #83
	BL VGA_write_char_ASM
	MOV r0, #42
	MOV r2, #76
	BL VGA_write_char_ASM
	MOV r0, #43
	MOV r2, #65
	BL VGA_write_char_ASM
	MOV r0, #44
	MOV r2, #89
	BL VGA_write_char_ASM
	B end_result 
end_result: 
	B timer	
timer:
	LDR r0, =f_bit_register
	MOV r1, #1 
	STR r1, [r0]
	LDR r0, =timer_config
	LDR r0, [r0]
	LDR r1, =timer_load_register
	STR r0, [r1] 
	LDR r2, =timer_control_register
	LDR r3, [r2]
	LSR r3, #2
	LSL r3, #2
	ADD r3, #0b01
	STR r3, [r2]
	LDR r0, =f_bit_register
	B loop_timer
loop_timer: 
	LDR r1, [r0]
	CMP r1, #1
	BEQ reset
	B loop_timer
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@ this subroutine is called at the start of the program in order to read the
@ input from user as to which map it should supply
@ we do this by polling the PS/2 data register
@ further it returns the value that corresponds to the map number 
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
waiting_for_map_input:
	MOV r1, #0 @our counter for how many times we have taken the data from ps/2
	LDR r2, =ps2_store @a copy of the address that shouldnt change
	MOV r3, #0 @we use r3 to examine the data that the ps2 put in our ps2_store
	MOV r4, #0 @we will accumalate the break code here
	MOV r5, #0 @we will use this to check if we have a valid last break code
	B input_loop2
input_loop2: 
	LDR r0, =ps2_store
	BL read_PS2_data_ASM
	CMP r0, #1
	LDREQ r3, [r2]
	CMP r1, #0 
	BEQ our_first_read2
	CMP r1, #1
	BEQ our_second_read2
our_first_read2:
	CMP r3, #0xf0
	BNE waiting_for_map_input
	MOV r4, r3
	ADD r1, r1, #1
	B input_loop2
our_second_read2:
	CMP r3, #0x16
	ADDEQ r5, #0x1
	BEQ full_click_registered2
	
	CMP r3, #0x1e
	ADDEQ r5, #0x2
	BEQ full_click_registered2
	
	CMP r3, #0x26
	ADDEQ r5, #0x3
	BEQ full_click_registered2
	
	CMP r3, #0x25
	ADDEQ r5, #0x4
	BEQ full_click_registered2
	
	CMP r3, #0x2e
	ADDEQ r5, #0x5
	BEQ full_click_registered2
	
	CMP r3, #0x36
	ADDEQ r5, #0x6
	BEQ full_click_registered2
	
	CMP r3, #0x3d
	ADDEQ r5, #0x7
	BEQ full_click_registered2
	
	CMP r3, #0x3e
	ADDEQ r5, #0x8
	BEQ full_click_registered2
	
	CMP r3, #0x46
	ADDEQ r5, #0x9
	BEQ full_click_registered2		 
	B waiting_for_map_input	
full_click_registered2: 
	LSL r4, #8
	ADD r4, r4, r3
	B starting_game
	
_start:
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@ This subroutine cleans the display and calls the polling subroutine for 
@ a user to input a map number 
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
reset: 	
	BL VGA_clear_charbuff_ASM
	BL VGA_fill_ASM
	BL draw_grid_ASM
	B waiting_for_map_input
	
	
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@ this subroutine signifies the start of our game, and then polls the PS/2 data
@ register in the same fashion as described before, and then drives the values of x 
@ and y coordinates through to the move function and keeps track of the position of the 
@ampersand. This is done using a variety of subroutines/drivers that relay the coordinates
@ back and forth to the relevent drivers
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
starting_game: 
	@ we have the game number in r5
	MOV r0, r5
	BL fill_grid_ASM
	MOV r0, #0
	MOV r1, #0 
	BL draw_ampersand_ASM
	BL draw_exit_ASM
	B waiting_driver    @ at this point we still have the game number in r5 and we 
						@ and coordinates in r0 and r1
	B end	
waiting_driver: 
	PUSH {r0, r1, r5} 
	B waiting_for_input	
waiting_for_input: 
	MOV r1, #0 @our counter for how many times we have taken the data from ps/2
	LDR r2, =ps2_store @a copy of the address that shouldnt change
	MOV r3, #0 @we use r3 to examine the data that the ps2 put in our ps2_store
	MOV r4, #0 @we will accumalate the break code here
	MOV r5, #0 @we will use this to check if we have a valid last break code
	B input_loop	
input_loop: 
	LDR r0, =ps2_store
	BL read_PS2_data_ASM
	CMP r0, #1
	LDREQ r3, [r2]
	CMP r1, #0 
	BEQ our_first_read
	CMP r1, #1
	BEQ our_second_read
	@CMP r1, #2
	@BEQ our_third_read	
@our_first_read:
	@CMP r3, #0xe0
	@BNE waiting_for_input
	@MOV r4, r3
	@ADD r1, r1, #1
	@B input_loop	
our_first_read:
	CMP r3, #0xf0
	BNE waiting_for_input
	LSL r4, #8
	ADD r4, r4, r3
	ADD r1, r1, #1
	B input_loop	
our_second_read:
	CMP r3, #0x75
	ADDEQ r5, #0x1
	BEQ full_click_registered
	
	CMP r3, #0x6b
	ADDEQ r5, #0x10
	BEQ full_click_registered
	
	CMP r3, #0x72
	ADDEQ r5, #0x100
	BEQ full_click_registered
	
	CMP r3, #0x74
	ADDEQ r5, #0x1000
	BEQ full_click_registered
	 
	B waiting_for_input
full_click_registered: 
	LSL r4, #8
	ADD r4, r4, r3
	b decode_move
	
decode_move: 
	POP {r0, r1, r5}
	MOV r2, r5
	AND r4, r4, #0xff
	
	CMP r4, #0x75 @up 
	SUBEQ r1, r1, #1
	BLEQ move_ASM
	CMP r3, #0
	ADDEQ r1, r1, #1
	
	CMP r4, #0x6b @left
	SUBEQ r0, r0, #1
	BLEQ move_ASM 
	CMP r3, #0
	ADDEQ r0, r0, #1
	
	CMP r4, #0x72 @down 
	ADDEQ r1, r1, #1
	BLEQ move_ASM
	CMP r3, #0
	SUBEQ r1, r1, #1
	
	CMP r4, #0x74 @right
	ADDEQ r0, r0, #1
	BLEQ move_ASM
	CMP r3, #0
	SUBEQ r0, r0, #1
	
	B waiting_driver	
	
	