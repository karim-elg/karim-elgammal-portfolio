.global _start

// Sider Switches Driver
// returns the state of slider switches in R0
.equ SW_MEMORY, 0xFF200040
/* The EQU directive gives a symbolic name to a numeric constant, 
a register-relative value or a PC-relative value. */
read_slider_switches_ASM:
    LDR R1, =SW_MEMORY
    LDR R0, [R1]
    BX  LR
	
// LEDs Driver
// writes the state of LEDs (On/Off state) in R0 to the LEDs memory location
.equ LED_MEMORY, 0xFF200000
write_LEDs_ASM:
    LDR R1, =LED_MEMORY
    STR R0, [R1]
    BX  LR
	
	
	

.equ HEX_DISPLAY_MEMORY, 0xFF200020
.equ HEX_DISPLAY_MEMORY2, 0xFF200030
.equ KEY_DATA_REGISTER_MEMORY, 0xFF200050
.equ KEY_EDGECAPTURE_REGISTER_MEMORY, 0xFF20005C
.equ KEY_INTERRUPT_REGISTER_MEMORY, 0xFF200058


@this array is used to 'decode' a value into the representation on the hex
array_of_effective_hex: .word 63, 6, 91, 79, 102, 109, 125, 7, 127, 111, 119, 124, 57, 94, 121, 113

@the clear loops help us recieve indicies and go through the hex displays to either clear or flood					
clear_loop:	     			 
				  TST R0, R1
				  STRNEB R3, [R4], #1
				  LSL R1, #1		 
				  CMP R1,#0x8
				  BLE clear_loop
				  LDR R4, =HEX_DISPLAY_MEMORY
				  BGT clear_loop2 
@clear_loop goes through the first four displays, then clear_loop2 takes care of the last two
clear_loop2:  
				TST R0, R1
			    STRNEB R3, [R5], #1
				LSL R1, #1
				CMP R1, #0x3f
				BLE clear_loop2
				LDR R5, =HEX_DISPLAY_MEMORY2 
				BX LR	
				
	
HEX_clear_ASM: 
				 PUSH {R0 - R5, LR}
				 LDR R0, [SP]
				 @MOV R0, #0x3f @ignore this it is for testing	 
				 MOV R1, #1 @the starting bit to check is 1, this is the register to increment
				 MOV R2, #0x8 @move immediate value of 32 into our counter	
				 MOV R3, #0x00 @number to clear hex (active high)
				 LDR R4, =HEX_DISPLAY_MEMORY
				 LDR R5, =HEX_DISPLAY_MEMORY2
				 BL clear_loop
				 POP {R0 - R5, LR}
				 BX LR
				 
HEX_flood_ASM: 
				 PUSH {R0 - R5, LR}
				 @MOV R0, #0x3f @ignore this it is for testing	
				 LDR R0, [SP]
				 MOV R1, #1 @the starting bit to check is 1, this is the register to increment
				 MOV R2, #0x8 @move immediate value of 8 into our counter	
				 MOV R3, #0xff @number to clear hex (active high)
				 LDR R4, =HEX_DISPLAY_MEMORY
				 LDR R5, =HEX_DISPLAY_MEMORY2
				 BL clear_loop
				 POP {R0 - R5, LR}
				 BX LR
				 
				
@this helper function allows us to exit and enter loops without affecting the counters 				
helper_function2: 
					
					PUSH {R3, R5, R6, R7, LR}
					B num_finder
					 
@this subroutine matches our number value with the HEX segment display code supplied by array_of_effective_hex			 
num_finder: 
			 
             CMP R6, R1 @compare our current number counter with our goal number
			 LDREQ R7, [R5]
			 ADDNE R5, R5, #4
			 ADDNE R6, R6, #1
			 BNE num_finder
			 BEQ write_loop
			 
			 

@this subroutine then stores the hex segment display code to the respective hex display			 
write_loop:   TST R0, R2
			   STRNEB R7, [R3]
			   LSL R2, #1
			   CMP R2, #0x8
			   ADD R3, R3, #1
			   BLE write_loop
			   POP {R3, R5, R6, R7, LR}
			   BX LR
			   
@similar to flood and clear, this is a driver to complete the respective loops that find the value hex code, then write to the displays in memory			   
HEX_write_ASM: 

				PUSH {V2, V3}
				MOV R2, #1
				LDR R3, =HEX_DISPLAY_MEMORY

				LDR R5, =array_of_effective_hex
				MOV R6, #0
				
				PUSH {R3, R5, R6, R7, LR}
				BL helper_function2
				POP {R3, R5, R6, R7, LR}
				MOV R2, #0
				MOV R3, #0
				MOV R5, #0
				MOV R6, #0
				
				POP {V2, V3}
				BX LR


@reads the Data register of the PB device by incrementing through the buttons and checking if their bit is 1 or not 
read_PB_data_ASM:  

					 PUSH {R0 - R3, LR}
					 MOV R0, #0
					 LDR R3, =KEY_DATA_REGISTER_MEMORY
					 LDR R1, [R3] 
					 MOV R2, #1 
					 TST R1, R2
					 ADDNE R0, R2, R0
					 
					 LSL R2, #1
					 TST R1, R2
					 ADDNE R0, R2, R0
					 
					 LSL R2, #1
					 TST R1, R2
					 ADDNE R0, R2, R0
					 
					 LSL R2, #1
					 TST R1, R2
					 ADDNE R0, R2, R0
					 
					 STR R0, [SP, #20]
					 
					 
					 POP {R0-R3, LR}				 
					 
					 
					 BX LR
					 
PB_data_is_pressed_ASM:  @argument is in r1
							BL read_PB_data_ASM
							TST R0, R1
							MOV R2, #0
							MOVNE R2, #0x1 @returns to R2

read_PB_edgecp_ASM:
						
					 PUSH {R0 - R3, LR}
					 MOV R0, #0
					 LDR R3, =KEY_EDGECAPTURE_REGISTER_MEMORY
					 LDR R1, [R3] 
					 MOV R2, #1 
					 TST R1, R2
					 ADDNE R0, R2, R0
					 
					 LSL R2, #1
					 TST R1, R2
					 ADDNE R0, R2, R0
					 
					 LSL R2, #1
					 TST R1, R2
					 ADDNE R0, R2, R0
					 
					 LSL R2, #1
					 TST R1, R2
					 ADDNE R0, R2, R0 @return value at R0
					 
					 STR R0, [SP, #20]				 
				     POP {R0-R3, LR}				 

					 
					 BX LR
					
PB_edgecp_is_pressed_ASM:    MOV R1, #0x2
								@argument is in R1
								BL read_PB_edgecp_ASM
								TST R0, R1 
								MOV R2, #0
								MOVNE R2, #0x1
PB_clear_edgecp_ASM: PUSH {R3, R4}
						LDR R3, =KEY_EDGECAPTURE_REGISTER_MEMORY
					    LDR R4, [R3]
						STR R4, [R3]
						POP {R3, R4}
						BX LR
						
enable_PB_INT_ASM: @ argument is in R1 index of which button
					
					 LDR R0, =KEY_INTERRUPT_REGISTER_MEMORY
					 LDR R2, [R0]
					 MOV R4, #0x1
					 
					 TST R1, #0x1 
					 ADDNE R3, R3, #1
					 LSLEQ R3, #1
					 
					 TST R1, #0x2
					 ADDNE R3, R3, #1
					 LSLEQ R3, #1

					 TST R1, #0x4
					 ADDNE R3, R3, #1
					 LSLEQ R3, #1
					 
					 TST R1, #0x8
					 ADDNE R3, R3, #1
					 LSLEQ R3, #1
					 
					 STRB R3, [R0]
					 
disable_PB_INT_ASM: 
				     @ argument is in R1 index of which button
					
					 LDR R0, =KEY_INTERRUPT_REGISTER_MEMORY
					 LDR R2, [R0]
					 MOV R4, #0x1
					 
					 TST R1, #0x1 
					 LSLNE R3, #1
					 ADDEQ R3, R3, #1
					 
					 TST R1, #0x2
					 LSLNE R3, #1
					 ADDEQ R3, R3, #1

					 TST R1, #0x4
					 LSLNE R3, #1
					 ADDEQ R3, R3, #1
					 
					 TST R1, #0x8
					 LSLNE R3, #1
					 ADDEQ R3, R3, #1
					 
					 STRB R3, [R0]
					 					
_start:		

@polls the device to check if the number is ready to be displayed or not
check_loop: 

		MOV R0, #0x30	
		BL HEX_flood_ASM 
		
	    BL read_slider_switches_ASM
		MOV V3, R0
		
		LSR V3, #9
		
		TST V3, #0x1
		MOVNE R0, #0xf
		TST V3, #0x1
		BLNE HEX_clear_ASM
		
		BL read_slider_switches_ASM
		
		BL write_LEDs_ASM
		
		@ now we need to check if the buttons have been released, if they have, display if not loop until they are 
		
		PUSH {V2} 
		BL read_PB_edgecp_ASM
		POP {V2}
		
		TST V2, #0
		
		BNE check_loop
		
		PUSH {R0, R1, V2}
		BEQ display_numbers
		
@ this subroutine calls the HEX_write_ASM 		
display_numbers: 
				@we need to move the indicies of hex into R0 and the value into R1 
				
				LDR R1, [SP]
				LDR R0, [SP, #8]			
				BL HEX_write_ASM
				BL PB_clear_edgecp_ASM
				POP {R0, R1, V2}
				B check_loop