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
-- Generated on "10/18/2021 20:27:14"
                                                            
-- Vhdl Test Bench template for design  :  bcd_adder_structural
-- 
-- Simulation tool : ModelSim-Altera (VHDL)
-- 

LIBRARY ieee;                                               
USE ieee.std_logic_1164.all; 
use ieee.numeric_std.all;                               

ENTITY bcd_adder_structural_vhd_tst IS
END bcd_adder_structural_vhd_tst;
ARCHITECTURE bcd_adder_structural_arch OF bcd_adder_structural_vhd_tst IS
-- constants                                                 
-- signals                                                   
SIGNAL A : STD_LOGIC_VECTOR(3 DOWNTO 0);
SIGNAL B : STD_LOGIC_VECTOR(3 DOWNTO 0);
SIGNAL C : STD_LOGIC;
SIGNAL S : STD_LOGIC_VECTOR(3 DOWNTO 0);
COMPONENT bcd_adder_structural
	PORT (
	A : IN STD_LOGIC_VECTOR(3 DOWNTO 0);
	B : IN STD_LOGIC_VECTOR(3 DOWNTO 0);
	C : OUT STD_LOGIC;
	S : OUT STD_LOGIC_VECTOR(3 DOWNTO 0)
	);
END COMPONENT;
BEGIN
	i1 : bcd_adder_structural
	PORT MAP (
-- list connections between master ports and signals
	A => A,
	B => B,
	C => C,
	S => S
	);
                                           
generate_test : process	  
 
   begin        
      A <= "1001"; B <= "1001";  wait for 100 ns;
        A <= "1000"; B <= "1001"; wait for 100 ns;
        A <= "0101"; B <= "1001"; wait for 100 ns;
        A <= "0011"; B <= "1001"; wait for 100 ns;
        A <= "1001"; B <= "0000"; wait for 100 ns;
        A <= "1001"; B <= "0111"; wait for 100 ns;
        A <= "0110"; B <= "0011"; wait for 100 ns;
      wait;
                                                       
END PROCESS generate_test;                                           
END bcd_adder_structural_arch;
