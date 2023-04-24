-- Copyright (C) 2016  Intel Corporation. All rights reserved.
-- Your use of Intel Corporation's design tools, logic functions 
-- and other software and tools, and its AMPP partner logic 
-- functions, and any output files from any of the foregoing 
-- (including device programming or simulation files), and any 
-- associated documentation or information are expressly subject 
-- to the terms and conditions of the Intel Program License 
-- Subscription Agreement, the Intel Quartus Prime License Agreement,
-- the Intel MegaCore Function License Agreement, or other 
-- applicable license agreement, including, without limitation, 
-- that your use is for the sole purpose of programming logic 
-- devices manufactured by Intel and sold by Intel or its 
-- authorized distributors.  Please refer to the applicable 
-- agreement for further details.

-- ***************************************************************************
-- This file contains a Vhdl test bench template that is freely editable to   
-- suit user's needs .Comments are provided in each section to help the user  
-- fill out necessary details.                                                
-- ***************************************************************************
-- Generated on "11/30/2021 19:32:14"
                                                            
-- Vhdl Test Bench template for design  :  karim_elgammal_comparator
-- 
-- Simulation tool : ModelSim-Altera (VHDL)
-- 

LIBRARY ieee;               
use ieee.numeric_std.all;                                
USE ieee.std_logic_1164.all;                                

ENTITY karim_elgammal_comparator_vhd_tst IS
END karim_elgammal_comparator_vhd_tst;
ARCHITECTURE karim_elgammal_comparator_arch OF karim_elgammal_comparator_vhd_tst IS
-- constants                                                 
-- signals                                                   
SIGNAL A : STD_LOGIC_VECTOR(3 DOWNTO 0);
SIGNAL AeqBplusOne : STD_LOGIC;
SIGNAL AgtBplusOne : STD_LOGIC;
SIGNAL AgteBplusOne : STD_LOGIC;
SIGNAL AltBplusOne : STD_LOGIC;
SIGNAL AlteBplusOne : STD_LOGIC;
SIGNAL B : STD_LOGIC_VECTOR(3 DOWNTO 0);
SIGNAL overflow : STD_LOGIC;
COMPONENT karim_elgammal_comparator
	PORT (
	A : IN STD_LOGIC_VECTOR(3 DOWNTO 0);
	AeqBplusOne : OUT STD_LOGIC;
	AgtBplusOne : OUT STD_LOGIC;
	AgteBplusOne : OUT STD_LOGIC;
	AltBplusOne : OUT STD_LOGIC;
	AlteBplusOne : OUT STD_LOGIC;
	B : IN STD_LOGIC_VECTOR(3 DOWNTO 0);
	overflow : OUT STD_LOGIC
	);
END COMPONENT;
BEGIN
	i1 : karim_elgammal_comparator
	PORT MAP (
-- list connections between master ports and signals
	A => A,
	AeqBplusOne => AeqBplusOne,
	AgtBplusOne => AgtBplusOne,
	AgteBplusOne => AgteBplusOne,
	AltBplusOne => AltBplusOne,
	AlteBplusOne => AlteBplusOne,
	B => B,
	overflow => overflow
	);
generate_test : PROCESS 
BEGIN 
A <= "0101";
FOR j IN 0 to 16 LOOP -- loop over all B values
B <= std_logic_vector(to_unsigned(j,4)); -- convert the loop variable i to

WAIT FOR 10 ns; -- suspend process for 10 nanoseconds at the start of each loop
END LOOP; -- end the j loop END LOOP; -- end the i loop

Wait;
end process generate_test;                                          
END karim_elgammal_comparator_arch;
