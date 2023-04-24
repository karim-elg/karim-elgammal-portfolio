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
-- Generated on "09/21/2022 13:48:14"
                                                            
-- Vhdl Test Bench template for design  :  karim_elgammal_comp
-- 
-- Simulation tool : ModelSim-Altera (VHDL)
-- 

LIBRARY ieee;                                               
USE ieee.std_logic_1164.all;   
USE ieee.numeric_std.all;                              

ENTITY karim_elgammal_comp_vhd_tst IS
END karim_elgammal_comp_vhd_tst;
ARCHITECTURE karim_elgammal_comp_arch OF karim_elgammal_comp_vhd_tst IS
-- constants                                                 
-- signals                                                   
SIGNAL A : STD_LOGIC_VECTOR(3 DOWNTO 0);
SIGNAL AeqB : STD_LOGIC;
SIGNAL B : STD_LOGIC_VECTOR(3 DOWNTO 0);
COMPONENT karim_elgammal_comp
	PORT (
	A : IN STD_LOGIC_VECTOR(3 DOWNTO 0);
	AeqB : OUT STD_LOGIC;
	B : IN STD_LOGIC_VECTOR(3 DOWNTO 0)
	);
END COMPONENT;
BEGIN
	i1 : karim_elgammal_comp
	PORT MAP (
-- list connections between master ports and signals
	A => A,
	AeqB => AeqB,
	B => B
	);
                                          
generate_test : PROCESS                                              
                                    
BEGIN                                                         
	for i in 0 to 16 loop
	A <= std_logic_vector(to_unsigned(i,4));
	
		for j in 0 to 16 loop
			B <= std_logic_vector(to_unsigned(j,4)); 	
			wait for 10 ns; 
			
		end loop;
	end loop; 
wait;	
END PROCESS generate_test;                                          
END karim_elgammal_comp_arch;
