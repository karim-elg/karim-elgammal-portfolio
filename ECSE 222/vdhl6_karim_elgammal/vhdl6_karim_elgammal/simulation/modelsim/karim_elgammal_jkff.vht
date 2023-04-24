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
-- Generated on "11/19/2021 13:48:38"
                                                            
-- Vhdl Test Bench template for design  :  karim_elgammal_jkff
-- 
-- Simulation tool : ModelSim-Altera (VHDL)
-- 

LIBRARY ieee;                                               
USE ieee.std_logic_1164.all;                                

ENTITY karim_elgammal_jkff_vhd_tst IS
END karim_elgammal_jkff_vhd_tst;
ARCHITECTURE karim_elgammal_jkff_arch OF karim_elgammal_jkff_vhd_tst IS
-- constants                                                 
-- signals                                                   
SIGNAL clk : STD_LOGIC;
SIGNAL J : STD_LOGIC;
SIGNAL K : STD_LOGIC;
SIGNAL Q : STD_LOGIC;
COMPONENT karim_elgammal_jkff
	PORT (
	clk : IN STD_LOGIC;
	J : IN STD_LOGIC;
	K : IN STD_LOGIC;
	Q : BUFFER STD_LOGIC
	);
END COMPONENT;
BEGIN
	i1 : karim_elgammal_jkff
	PORT MAP (
-- list connections between master ports and signals
	clk => clk,
	J => J,
	K => K,
	Q => Q
	);
	
clock_generation : process
begin
clk <= '1';
wait for 5 ns;
clk <= '0';
wait for 5 ns;
end process clock_generation;
                                           
always: process
begin
J <= '0';
K <= '0';
wait for 10 ns;

J <= '0';
K <= '1';
wait for 10 ns;

J <= '1';
K <= '0';
wait for 10 ns;

J <= '1';
K <= '1';
wait for 10 ns;

J <= '1';
K <= '1';
wait for 10 ns;

J <= '0';
K <= '0';
wait for 10 ns;

J <= '0';
K <= '0';
wait for 10 ns;
end process;
                                         
END karim_elgammal_jkff_arch;
