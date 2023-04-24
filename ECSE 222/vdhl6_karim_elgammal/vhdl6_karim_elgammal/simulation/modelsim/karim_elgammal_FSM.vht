-- Copyright (C) 1991-2015 Altera Corporation. All rights reserved.
-- Your use of Altera Corporation's design tools, logic functions 
-- and other software and tools, and its AMPP partner logic 
-- functions, and any output files from any of the foregoing 
-- (including device programming or simulation files), and any 
-- associated documentation or information are expressly subject 
-- to the terms and conditions of the Altera Program License 
-- Subscription Agreement, the Altera Quartus Prime License Agreement,
-- the Altera MegaCore Function License Agreement, or other 
-- applicable license agreement, including, without limitation, 
-- that your use is for the sole purpose of programming logic 
-- devices manufactured by Altera and sold by Altera or its 
-- authorized distributors.  Please refer to the applicable 
-- agreement for further details.

-- ***************************************************************************
-- This file contains a Vhdl test bench template that is freely editable to   
-- suit user's needs .Comments are provided in each section to help the user  
-- fill out necessary details.                                                
-- ***************************************************************************
-- Generated on "12/01/2022 14:15:00"
                                                            
-- Vhdl Test Bench template for design  :  karim_elgammal_FSM
-- 
-- Simulation tool : ModelSim-Altera (VHDL)
-- 

LIBRARY ieee;                                               
USE ieee.std_logic_1164.all;                                

ENTITY karim_elgammal_FSM_vhd_tst IS
END karim_elgammal_FSM_vhd_tst;
ARCHITECTURE karim_elgammal_FSM_arch OF karim_elgammal_FSM_vhd_tst IS
-- constants                                                 
-- signals                                                   
SIGNAL clk : STD_LOGIC;
SIGNAL enable : STD_LOGIC;
SIGNAL out_1 : STD_LOGIC;
SIGNAL out_2 : STD_LOGIC;
SIGNAL reset : STD_LOGIC;
SIGNAL seq : STD_LOGIC;
COMPONENT karim_elgammal_FSM
	PORT (
	clk : IN STD_LOGIC;
	enable : IN STD_LOGIC;
	out_1 : BUFFER STD_LOGIC;
	out_2 : BUFFER STD_LOGIC;
	reset : IN STD_LOGIC;
	seq : IN STD_LOGIC
	);
END COMPONENT;
BEGIN
	i1 : karim_elgammal_FSM
	PORT MAP (
-- list connections between master ports and signals
	clk => clk,
	enable => enable,
	out_1 => out_1,
	out_2 => out_2,
	reset => reset,
	seq => seq
	);
init : PROCESS                                               
-- variable declarations                                     
BEGIN                                                        
        -- code that executes only once                      
WAIT;                                                       
END PROCESS init;                                           
always : PROCESS                                              
-- optional sensitivity list                                  
-- (        )                                                 
-- variable declarations                                      
BEGIN                                                         
        -- code executes for every event on sensitivity list  
WAIT;                                                        
END PROCESS always;                                          
END karim_elgammal_FSM_arch;
