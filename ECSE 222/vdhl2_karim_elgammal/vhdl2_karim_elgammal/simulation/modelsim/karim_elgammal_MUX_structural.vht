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
-- Generated on "09/21/2022 14:55:33"
                                                            
-- Vhdl Test Bench template for design  :  karim_elgammal_MUX_structural
-- 
-- Simulation tool : ModelSim-Altera (VHDL)
-- 

LIBRARY ieee;                                               
USE ieee.std_logic_1164.all;                                

ENTITY karim_elgammal_MUX_structural_vhd_tst IS
END karim_elgammal_MUX_structural_vhd_tst;
ARCHITECTURE karim_elgammal_MUX_structural_arch OF karim_elgammal_MUX_structural_vhd_tst IS
-- constants                                                 
-- signals                                                   
SIGNAL A : STD_LOGIC;
SIGNAL B : STD_LOGIC;
SIGNAL S : STD_LOGIC;
SIGNAL Y : STD_LOGIC;
COMPONENT karim_elgammal_MUX_structural
	PORT (
	A : IN STD_LOGIC;
	B : IN STD_LOGIC;
	S : IN STD_LOGIC;
	Y : OUT STD_LOGIC
	);
END COMPONENT;
BEGIN
	i1 : karim_elgammal_MUX_structural
	PORT MAP (
-- list connections between master ports and signals
	A => A,
	B => B,
	S => S,
	Y => Y
	);
                                           
generate_test : PROCESS                                              
                                    
BEGIN                                                         
	A <= '0'; 
	B <= '0'; 
	S <= '0'; 
wait for 10 ns; 
	A <= '0'; 
	B <= '0'; 
	S <= '1';
wait for 10 ns; 
	A <= '0'; 
	B <= '1'; 
	S <= '0';
wait for 10 ns; 
	A <= '0'; 
	B <= '1'; 
	S <= '1';
wait for 10 ns; 
	A <= '1'; 
	B <= '0'; 
	S <= '0';
wait for 10 ns; 
	A <= '1'; 
	B <= '0'; 
	S <= '1';
wait for 10 ns; 
	A <= '1'; 
	B <= '1'; 
	S <= '0';
wait for 10 ns; 
	A <= '1'; 
	B <= '1'; 
	S <= '1';
wait;	
END PROCESS generate_test;                                         
END karim_elgammal_MUX_structural_arch;
