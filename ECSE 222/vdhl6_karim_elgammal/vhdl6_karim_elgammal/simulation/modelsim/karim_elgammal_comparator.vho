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

-- VENDOR "Altera"
-- PROGRAM "Quartus Prime"
-- VERSION "Version 15.1.0 Build 185 10/21/2015 SJ Standard Edition"

-- DATE "12/01/2022 22:39:16"

-- 
-- Device: Altera 5CSEMA5F31C6 Package FBGA896
-- 

-- 
-- This VHDL file should be used for ModelSim-Altera (VHDL) only
-- 

LIBRARY ALTERA;
LIBRARY ALTERA_LNSIM;
LIBRARY CYCLONEV;
LIBRARY IEEE;
USE ALTERA.ALTERA_PRIMITIVES_COMPONENTS.ALL;
USE ALTERA_LNSIM.ALTERA_LNSIM_COMPONENTS.ALL;
USE CYCLONEV.CYCLONEV_COMPONENTS.ALL;
USE IEEE.STD_LOGIC_1164.ALL;

ENTITY 	karim_elgammal_wrapper IS
    PORT (
	reset : IN std_logic;
	enable : IN std_logic;
	clk : IN std_logic;
	HEX0 : OUT std_logic_vector(6 DOWNTO 0);
	HEX5 : OUT std_logic_vector(6 DOWNTO 0)
	);
END karim_elgammal_wrapper;

-- Design Ports Information
-- HEX0[0]	=>  Location: PIN_AE26,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- HEX0[1]	=>  Location: PIN_AE27,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- HEX0[2]	=>  Location: PIN_AE28,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- HEX0[3]	=>  Location: PIN_AG27,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- HEX0[4]	=>  Location: PIN_AF28,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- HEX0[5]	=>  Location: PIN_AG28,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- HEX0[6]	=>  Location: PIN_AH28,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- HEX5[0]	=>  Location: PIN_V25,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- HEX5[1]	=>  Location: PIN_AA28,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- HEX5[2]	=>  Location: PIN_Y27,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- HEX5[3]	=>  Location: PIN_AB27,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- HEX5[4]	=>  Location: PIN_AB26,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- HEX5[5]	=>  Location: PIN_AA26,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- HEX5[6]	=>  Location: PIN_AA25,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- enable	=>  Location: PIN_AE12,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- reset	=>  Location: PIN_AA14,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- clk	=>  Location: PIN_AF14,	 I/O Standard: 2.5 V,	 Current Strength: Default


ARCHITECTURE structure OF karim_elgammal_wrapper IS
SIGNAL gnd : std_logic := '0';
SIGNAL vcc : std_logic := '1';
SIGNAL unknown : std_logic := 'X';
SIGNAL devoe : std_logic := '1';
SIGNAL devclrn : std_logic := '1';
SIGNAL devpor : std_logic := '1';
SIGNAL ww_devoe : std_logic;
SIGNAL ww_devclrn : std_logic;
SIGNAL ww_devpor : std_logic;
SIGNAL ww_reset : std_logic;
SIGNAL ww_enable : std_logic;
SIGNAL ww_clk : std_logic;
SIGNAL ww_HEX0 : std_logic_vector(6 DOWNTO 0);
SIGNAL ww_HEX5 : std_logic_vector(6 DOWNTO 0);
SIGNAL \~QUARTUS_CREATED_GND~I_combout\ : std_logic;
SIGNAL \clk~input_o\ : std_logic;
SIGNAL \clk~inputCLKENA0_outclk\ : std_logic;
SIGNAL \clk_div|Add0~73_sumout\ : std_logic;
SIGNAL \clk_div|temp[0]~10_combout\ : std_logic;
SIGNAL \reset~input_o\ : std_logic;
SIGNAL \enable~input_o\ : std_logic;
SIGNAL \clk_div|Add0~74\ : std_logic;
SIGNAL \clk_div|Add0~33_sumout\ : std_logic;
SIGNAL \clk_div|temp[1]~0_combout\ : std_logic;
SIGNAL \clk_div|Add0~34\ : std_logic;
SIGNAL \clk_div|Add0~37_sumout\ : std_logic;
SIGNAL \clk_div|temp[2]~1_combout\ : std_logic;
SIGNAL \clk_div|Add0~38\ : std_logic;
SIGNAL \clk_div|Add0~41_sumout\ : std_logic;
SIGNAL \clk_div|temp[3]~2_combout\ : std_logic;
SIGNAL \clk_div|Add0~42\ : std_logic;
SIGNAL \clk_div|Add0~45_sumout\ : std_logic;
SIGNAL \clk_div|temp[4]~3_combout\ : std_logic;
SIGNAL \clk_div|Add0~46\ : std_logic;
SIGNAL \clk_div|Add0~53_sumout\ : std_logic;
SIGNAL \clk_div|temp[5]~5_combout\ : std_logic;
SIGNAL \clk_div|Add0~54\ : std_logic;
SIGNAL \clk_div|Add0~49_sumout\ : std_logic;
SIGNAL \clk_div|temp[6]~4_combout\ : std_logic;
SIGNAL \clk_div|Add0~50\ : std_logic;
SIGNAL \clk_div|Add0~17_sumout\ : std_logic;
SIGNAL \clk_div|Add0~18\ : std_logic;
SIGNAL \clk_div|Add0~5_sumout\ : std_logic;
SIGNAL \clk_div|Add0~6\ : std_logic;
SIGNAL \clk_div|Add0~25_sumout\ : std_logic;
SIGNAL \clk_div|Add0~26\ : std_logic;
SIGNAL \clk_div|Add0~29_sumout\ : std_logic;
SIGNAL \clk_div|Add0~30\ : std_logic;
SIGNAL \clk_div|Add0~21_sumout\ : std_logic;
SIGNAL \clk_div|Add0~22\ : std_logic;
SIGNAL \clk_div|Add0~81_sumout\ : std_logic;
SIGNAL \clk_div|temp[12]~12_combout\ : std_logic;
SIGNAL \clk_div|Add0~82\ : std_logic;
SIGNAL \clk_div|Add0~85_sumout\ : std_logic;
SIGNAL \clk_div|temp[13]~13_combout\ : std_logic;
SIGNAL \clk_div|Add0~86\ : std_logic;
SIGNAL \clk_div|Add0~89_sumout\ : std_logic;
SIGNAL \clk_div|temp[14]~14_combout\ : std_logic;
SIGNAL \clk_div|Add0~90\ : std_logic;
SIGNAL \clk_div|Add0~93_sumout\ : std_logic;
SIGNAL \clk_div|temp[15]~15_combout\ : std_logic;
SIGNAL \clk_div|Add0~94\ : std_logic;
SIGNAL \clk_div|Add0~13_sumout\ : std_logic;
SIGNAL \clk_div|Add0~14\ : std_logic;
SIGNAL \clk_div|Add0~101_sumout\ : std_logic;
SIGNAL \clk_div|temp[17]~17_combout\ : std_logic;
SIGNAL \clk_div|Add0~102\ : std_logic;
SIGNAL \clk_div|Add0~9_sumout\ : std_logic;
SIGNAL \clk_div|Add0~10\ : std_logic;
SIGNAL \clk_div|Add0~97_sumout\ : std_logic;
SIGNAL \clk_div|temp[19]~16_combout\ : std_logic;
SIGNAL \clk_div|en_out~4_combout\ : std_logic;
SIGNAL \clk_div|en_out~2_combout\ : std_logic;
SIGNAL \clk_div|Add0~98\ : std_logic;
SIGNAL \clk_div|Add0~57_sumout\ : std_logic;
SIGNAL \clk_div|temp[20]~6_combout\ : std_logic;
SIGNAL \clk_div|Add0~58\ : std_logic;
SIGNAL \clk_div|Add0~61_sumout\ : std_logic;
SIGNAL \clk_div|temp[21]~7_combout\ : std_logic;
SIGNAL \clk_div|Add0~62\ : std_logic;
SIGNAL \clk_div|Add0~65_sumout\ : std_logic;
SIGNAL \clk_div|temp[22]~8_combout\ : std_logic;
SIGNAL \clk_div|Add0~66\ : std_logic;
SIGNAL \clk_div|Add0~69_sumout\ : std_logic;
SIGNAL \clk_div|temp[23]~9_combout\ : std_logic;
SIGNAL \clk_div|Add0~70\ : std_logic;
SIGNAL \clk_div|Add0~1_sumout\ : std_logic;
SIGNAL \clk_div|en_out~1_combout\ : std_logic;
SIGNAL \clk_div|Add0~2\ : std_logic;
SIGNAL \clk_div|Add0~77_sumout\ : std_logic;
SIGNAL \clk_div|temp[25]~11_combout\ : std_logic;
SIGNAL \clk_div|en_out~3_combout\ : std_logic;
SIGNAL \clk_div|en_out~0_combout\ : std_logic;
SIGNAL \seqgen|cnt[5]~DUPLICATE_q\ : std_logic;
SIGNAL \seqgen|cnt[0]~0_combout\ : std_logic;
SIGNAL \seqgen|Add0~25_sumout\ : std_logic;
SIGNAL \seqgen|cnt[1]~feeder_combout\ : std_logic;
SIGNAL \seqgen|Add0~26\ : std_logic;
SIGNAL \seqgen|Add0~5_sumout\ : std_logic;
SIGNAL \seqgen|Add0~6\ : std_logic;
SIGNAL \seqgen|Add0~21_sumout\ : std_logic;
SIGNAL \seqgen|Add0~22\ : std_logic;
SIGNAL \seqgen|Add0~13_sumout\ : std_logic;
SIGNAL \seqgen|cnt[4]~DUPLICATE_q\ : std_logic;
SIGNAL \seqgen|Add0~14\ : std_logic;
SIGNAL \seqgen|Add0~17_sumout\ : std_logic;
SIGNAL \seqgen|Add0~18\ : std_logic;
SIGNAL \seqgen|Add0~9_sumout\ : std_logic;
SIGNAL \seqgen|Add0~10\ : std_logic;
SIGNAL \seqgen|Add0~1_sumout\ : std_logic;
SIGNAL \seqgen|cnt[7]~DUPLICATE_q\ : std_logic;
SIGNAL \seqgen|Mux0~1_combout\ : std_logic;
SIGNAL \seqgen|Mux0~0_combout\ : std_logic;
SIGNAL \seqgen|Mux0~2_combout\ : std_logic;
SIGNAL \seqgen|Mux0~3_combout\ : std_logic;
SIGNAL \seqgen|Mux0~4_combout\ : std_logic;
SIGNAL \seqcounter|seq_detect|miniFSM1|Selector0~0_combout\ : std_logic;
SIGNAL \seqcounter|seq_detect|miniFSM1|currentState.A0~q\ : std_logic;
SIGNAL \seqcounter|seq_detect|miniFSM1|currentState~9_combout\ : std_logic;
SIGNAL \seqcounter|seq_detect|miniFSM1|currentState.A2~q\ : std_logic;
SIGNAL \seqcounter|seq_detect|miniFSM1|Selector3~0_combout\ : std_logic;
SIGNAL \seqcounter|seq_detect|miniFSM1|currentState.A3~q\ : std_logic;
SIGNAL \seqcounter|seq_detect|miniFSM1|Selector4~0_combout\ : std_logic;
SIGNAL \seqcounter|seq_detect|miniFSM1|currentState.A4~q\ : std_logic;
SIGNAL \seqcounter|counter1|j0|sigQ~0_combout\ : std_logic;
SIGNAL \seqcounter|counter1|j0|sigQ~feeder_combout\ : std_logic;
SIGNAL \seqcounter|counter1|j0|sigQ~q\ : std_logic;
SIGNAL \seqcounter|counter1|j1|sigQ~0_combout\ : std_logic;
SIGNAL \seqcounter|counter1|j1|sigQ~q\ : std_logic;
SIGNAL \seqcounter|counter1|j2|sigQ~0_combout\ : std_logic;
SIGNAL \seqcounter|counter1|j2|sigQ~q\ : std_logic;
SIGNAL \hexdis0|Mux6~0_combout\ : std_logic;
SIGNAL \hexdis0|Mux5~0_combout\ : std_logic;
SIGNAL \hexdis0|Mux4~0_combout\ : std_logic;
SIGNAL \hexdis0|Mux3~0_combout\ : std_logic;
SIGNAL \hexdis0|Mux2~0_combout\ : std_logic;
SIGNAL \hexdis0|Mux1~0_combout\ : std_logic;
SIGNAL \hexdis0|Mux0~0_combout\ : std_logic;
SIGNAL \seqcounter|seq_detect|miniFSM2|Selector2~2_combout\ : std_logic;
SIGNAL \seqcounter|seq_detect|miniFSM2|currentState.A0~q\ : std_logic;
SIGNAL \seqcounter|seq_detect|miniFSM2|Selector1~0_combout\ : std_logic;
SIGNAL \seqcounter|seq_detect|miniFSM2|currentState.A1~q\ : std_logic;
SIGNAL \seqcounter|seq_detect|miniFSM2|Selector2~1_combout\ : std_logic;
SIGNAL \seqcounter|seq_detect|miniFSM2|currentState.A2~q\ : std_logic;
SIGNAL \seqcounter|seq_detect|miniFSM2|Selector2~0_combout\ : std_logic;
SIGNAL \seqcounter|seq_detect|miniFSM2|currentState.A3~q\ : std_logic;
SIGNAL \seqcounter|seq_detect|miniFSM2|Selector4~0_combout\ : std_logic;
SIGNAL \seqcounter|seq_detect|miniFSM2|currentState.A4~q\ : std_logic;
SIGNAL \seqcounter|counter2|j0|sigQ~0_combout\ : std_logic;
SIGNAL \seqcounter|counter2|j0|sigQ~q\ : std_logic;
SIGNAL \seqcounter|counter2|j1|sigQ~0_combout\ : std_logic;
SIGNAL \seqcounter|counter2|j1|sigQ~q\ : std_logic;
SIGNAL \seqcounter|counter2|j2|sigQ~0_combout\ : std_logic;
SIGNAL \seqcounter|counter2|j2|sigQ~q\ : std_logic;
SIGNAL \hexdis5|Mux6~0_combout\ : std_logic;
SIGNAL \hexdis5|Mux5~0_combout\ : std_logic;
SIGNAL \hexdis5|Mux4~0_combout\ : std_logic;
SIGNAL \hexdis5|Mux3~0_combout\ : std_logic;
SIGNAL \hexdis5|Mux2~0_combout\ : std_logic;
SIGNAL \hexdis5|Mux1~0_combout\ : std_logic;
SIGNAL \hexdis5|Mux0~0_combout\ : std_logic;
SIGNAL \clk_div|temp\ : std_logic_vector(25 DOWNTO 0);
SIGNAL \seqgen|cnt\ : std_logic_vector(7 DOWNTO 0);
SIGNAL \seqcounter|seq_detect|miniFSM1|ALT_INV_currentState.A0~q\ : std_logic;
SIGNAL \seqcounter|seq_detect|miniFSM2|ALT_INV_currentState.A2~q\ : std_logic;
SIGNAL \clk_div|ALT_INV_en_out~0_combout\ : std_logic;
SIGNAL \clk_div|ALT_INV_en_out~4_combout\ : std_logic;
SIGNAL \clk_div|ALT_INV_temp\ : std_logic_vector(25 DOWNTO 0);
SIGNAL \clk_div|ALT_INV_en_out~3_combout\ : std_logic;
SIGNAL \clk_div|ALT_INV_en_out~2_combout\ : std_logic;
SIGNAL \clk_div|ALT_INV_en_out~1_combout\ : std_logic;
SIGNAL \seqcounter|seq_detect|miniFSM1|ALT_INV_currentState.A2~q\ : std_logic;
SIGNAL \seqcounter|seq_detect|miniFSM2|ALT_INV_currentState.A3~q\ : std_logic;
SIGNAL \seqgen|ALT_INV_Mux0~4_combout\ : std_logic;
SIGNAL \seqgen|ALT_INV_cnt\ : std_logic_vector(7 DOWNTO 0);
SIGNAL \seqgen|ALT_INV_Mux0~3_combout\ : std_logic;
SIGNAL \seqgen|ALT_INV_Mux0~2_combout\ : std_logic;
SIGNAL \seqgen|ALT_INV_Mux0~1_combout\ : std_logic;
SIGNAL \seqgen|ALT_INV_Mux0~0_combout\ : std_logic;
SIGNAL \seqcounter|seq_detect|miniFSM1|ALT_INV_currentState.A3~q\ : std_logic;
SIGNAL \seqcounter|seq_detect|miniFSM2|ALT_INV_currentState.A4~q\ : std_logic;
SIGNAL \seqcounter|counter2|j2|ALT_INV_sigQ~q\ : std_logic;
SIGNAL \seqcounter|counter2|j0|ALT_INV_sigQ~q\ : std_logic;
SIGNAL \seqcounter|counter2|j1|ALT_INV_sigQ~q\ : std_logic;
SIGNAL \seqcounter|counter1|j2|ALT_INV_sigQ~q\ : std_logic;
SIGNAL \seqcounter|counter1|j0|ALT_INV_sigQ~q\ : std_logic;
SIGNAL \seqcounter|counter1|j1|ALT_INV_sigQ~q\ : std_logic;
SIGNAL \clk_div|ALT_INV_Add0~101_sumout\ : std_logic;
SIGNAL \clk_div|ALT_INV_Add0~97_sumout\ : std_logic;
SIGNAL \clk_div|ALT_INV_Add0~93_sumout\ : std_logic;
SIGNAL \clk_div|ALT_INV_Add0~89_sumout\ : std_logic;
SIGNAL \clk_div|ALT_INV_Add0~85_sumout\ : std_logic;
SIGNAL \clk_div|ALT_INV_Add0~81_sumout\ : std_logic;
SIGNAL \clk_div|ALT_INV_Add0~77_sumout\ : std_logic;
SIGNAL \clk_div|ALT_INV_Add0~73_sumout\ : std_logic;
SIGNAL \clk_div|ALT_INV_Add0~69_sumout\ : std_logic;
SIGNAL \clk_div|ALT_INV_Add0~65_sumout\ : std_logic;
SIGNAL \clk_div|ALT_INV_Add0~61_sumout\ : std_logic;
SIGNAL \clk_div|ALT_INV_Add0~57_sumout\ : std_logic;
SIGNAL \clk_div|ALT_INV_Add0~53_sumout\ : std_logic;
SIGNAL \clk_div|ALT_INV_Add0~49_sumout\ : std_logic;
SIGNAL \clk_div|ALT_INV_Add0~45_sumout\ : std_logic;
SIGNAL \clk_div|ALT_INV_Add0~41_sumout\ : std_logic;
SIGNAL \clk_div|ALT_INV_Add0~37_sumout\ : std_logic;
SIGNAL \clk_div|ALT_INV_Add0~33_sumout\ : std_logic;
SIGNAL \seqgen|ALT_INV_Add0~25_sumout\ : std_logic;
SIGNAL \seqgen|ALT_INV_cnt[5]~DUPLICATE_q\ : std_logic;
SIGNAL \seqgen|ALT_INV_cnt[4]~DUPLICATE_q\ : std_logic;
SIGNAL \seqgen|ALT_INV_cnt[7]~DUPLICATE_q\ : std_logic;
SIGNAL \ALT_INV_enable~input_o\ : std_logic;
SIGNAL \seqcounter|counter1|j0|ALT_INV_sigQ~0_combout\ : std_logic;
SIGNAL \seqcounter|seq_detect|miniFSM2|ALT_INV_currentState.A0~q\ : std_logic;
SIGNAL \seqcounter|seq_detect|miniFSM2|ALT_INV_currentState.A1~q\ : std_logic;

BEGIN

ww_reset <= reset;
ww_enable <= enable;
ww_clk <= clk;
HEX0 <= ww_HEX0;
HEX5 <= ww_HEX5;
ww_devoe <= devoe;
ww_devclrn <= devclrn;
ww_devpor <= devpor;
\seqcounter|seq_detect|miniFSM1|ALT_INV_currentState.A0~q\ <= NOT \seqcounter|seq_detect|miniFSM1|currentState.A0~q\;
\seqcounter|seq_detect|miniFSM2|ALT_INV_currentState.A2~q\ <= NOT \seqcounter|seq_detect|miniFSM2|currentState.A2~q\;
\clk_div|ALT_INV_en_out~0_combout\ <= NOT \clk_div|en_out~0_combout\;
\clk_div|ALT_INV_en_out~4_combout\ <= NOT \clk_div|en_out~4_combout\;
\clk_div|ALT_INV_temp\(17) <= NOT \clk_div|temp\(17);
\clk_div|ALT_INV_temp\(19) <= NOT \clk_div|temp\(19);
\clk_div|ALT_INV_temp\(15) <= NOT \clk_div|temp\(15);
\clk_div|ALT_INV_temp\(14) <= NOT \clk_div|temp\(14);
\clk_div|ALT_INV_temp\(13) <= NOT \clk_div|temp\(13);
\clk_div|ALT_INV_temp\(12) <= NOT \clk_div|temp\(12);
\clk_div|ALT_INV_en_out~3_combout\ <= NOT \clk_div|en_out~3_combout\;
\clk_div|ALT_INV_temp\(25) <= NOT \clk_div|temp\(25);
\clk_div|ALT_INV_temp\(0) <= NOT \clk_div|temp\(0);
\clk_div|ALT_INV_temp\(23) <= NOT \clk_div|temp\(23);
\clk_div|ALT_INV_temp\(22) <= NOT \clk_div|temp\(22);
\clk_div|ALT_INV_temp\(21) <= NOT \clk_div|temp\(21);
\clk_div|ALT_INV_temp\(20) <= NOT \clk_div|temp\(20);
\clk_div|ALT_INV_en_out~2_combout\ <= NOT \clk_div|en_out~2_combout\;
\clk_div|ALT_INV_temp\(5) <= NOT \clk_div|temp\(5);
\clk_div|ALT_INV_temp\(6) <= NOT \clk_div|temp\(6);
\clk_div|ALT_INV_temp\(4) <= NOT \clk_div|temp\(4);
\clk_div|ALT_INV_temp\(3) <= NOT \clk_div|temp\(3);
\clk_div|ALT_INV_temp\(2) <= NOT \clk_div|temp\(2);
\clk_div|ALT_INV_temp\(1) <= NOT \clk_div|temp\(1);
\clk_div|ALT_INV_en_out~1_combout\ <= NOT \clk_div|en_out~1_combout\;
\seqcounter|seq_detect|miniFSM1|ALT_INV_currentState.A2~q\ <= NOT \seqcounter|seq_detect|miniFSM1|currentState.A2~q\;
\seqcounter|seq_detect|miniFSM2|ALT_INV_currentState.A3~q\ <= NOT \seqcounter|seq_detect|miniFSM2|currentState.A3~q\;
\seqgen|ALT_INV_Mux0~4_combout\ <= NOT \seqgen|Mux0~4_combout\;
\seqgen|ALT_INV_cnt\(1) <= NOT \seqgen|cnt\(1);
\seqgen|ALT_INV_cnt\(3) <= NOT \seqgen|cnt\(3);
\seqgen|ALT_INV_Mux0~3_combout\ <= NOT \seqgen|Mux0~3_combout\;
\seqgen|ALT_INV_Mux0~2_combout\ <= NOT \seqgen|Mux0~2_combout\;
\seqgen|ALT_INV_Mux0~1_combout\ <= NOT \seqgen|Mux0~1_combout\;
\seqgen|ALT_INV_Mux0~0_combout\ <= NOT \seqgen|Mux0~0_combout\;
\seqgen|ALT_INV_cnt\(5) <= NOT \seqgen|cnt\(5);
\seqgen|ALT_INV_cnt\(4) <= NOT \seqgen|cnt\(4);
\seqgen|ALT_INV_cnt\(6) <= NOT \seqgen|cnt\(6);
\seqgen|ALT_INV_cnt\(2) <= NOT \seqgen|cnt\(2);
\seqgen|ALT_INV_cnt\(7) <= NOT \seqgen|cnt\(7);
\seqgen|ALT_INV_cnt\(0) <= NOT \seqgen|cnt\(0);
\seqcounter|seq_detect|miniFSM1|ALT_INV_currentState.A3~q\ <= NOT \seqcounter|seq_detect|miniFSM1|currentState.A3~q\;
\seqcounter|seq_detect|miniFSM2|ALT_INV_currentState.A4~q\ <= NOT \seqcounter|seq_detect|miniFSM2|currentState.A4~q\;
\seqcounter|counter2|j2|ALT_INV_sigQ~q\ <= NOT \seqcounter|counter2|j2|sigQ~q\;
\seqcounter|counter2|j0|ALT_INV_sigQ~q\ <= NOT \seqcounter|counter2|j0|sigQ~q\;
\seqcounter|counter2|j1|ALT_INV_sigQ~q\ <= NOT \seqcounter|counter2|j1|sigQ~q\;
\seqcounter|counter1|j2|ALT_INV_sigQ~q\ <= NOT \seqcounter|counter1|j2|sigQ~q\;
\seqcounter|counter1|j0|ALT_INV_sigQ~q\ <= NOT \seqcounter|counter1|j0|sigQ~q\;
\seqcounter|counter1|j1|ALT_INV_sigQ~q\ <= NOT \seqcounter|counter1|j1|sigQ~q\;
\clk_div|ALT_INV_Add0~101_sumout\ <= NOT \clk_div|Add0~101_sumout\;
\clk_div|ALT_INV_Add0~97_sumout\ <= NOT \clk_div|Add0~97_sumout\;
\clk_div|ALT_INV_Add0~93_sumout\ <= NOT \clk_div|Add0~93_sumout\;
\clk_div|ALT_INV_Add0~89_sumout\ <= NOT \clk_div|Add0~89_sumout\;
\clk_div|ALT_INV_Add0~85_sumout\ <= NOT \clk_div|Add0~85_sumout\;
\clk_div|ALT_INV_Add0~81_sumout\ <= NOT \clk_div|Add0~81_sumout\;
\clk_div|ALT_INV_Add0~77_sumout\ <= NOT \clk_div|Add0~77_sumout\;
\clk_div|ALT_INV_Add0~73_sumout\ <= NOT \clk_div|Add0~73_sumout\;
\clk_div|ALT_INV_Add0~69_sumout\ <= NOT \clk_div|Add0~69_sumout\;
\clk_div|ALT_INV_Add0~65_sumout\ <= NOT \clk_div|Add0~65_sumout\;
\clk_div|ALT_INV_Add0~61_sumout\ <= NOT \clk_div|Add0~61_sumout\;
\clk_div|ALT_INV_Add0~57_sumout\ <= NOT \clk_div|Add0~57_sumout\;
\clk_div|ALT_INV_Add0~53_sumout\ <= NOT \clk_div|Add0~53_sumout\;
\clk_div|ALT_INV_Add0~49_sumout\ <= NOT \clk_div|Add0~49_sumout\;
\clk_div|ALT_INV_Add0~45_sumout\ <= NOT \clk_div|Add0~45_sumout\;
\clk_div|ALT_INV_Add0~41_sumout\ <= NOT \clk_div|Add0~41_sumout\;
\clk_div|ALT_INV_Add0~37_sumout\ <= NOT \clk_div|Add0~37_sumout\;
\clk_div|ALT_INV_Add0~33_sumout\ <= NOT \clk_div|Add0~33_sumout\;
\clk_div|ALT_INV_temp\(10) <= NOT \clk_div|temp\(10);
\clk_div|ALT_INV_temp\(9) <= NOT \clk_div|temp\(9);
\clk_div|ALT_INV_temp\(11) <= NOT \clk_div|temp\(11);
\clk_div|ALT_INV_temp\(7) <= NOT \clk_div|temp\(7);
\clk_div|ALT_INV_temp\(16) <= NOT \clk_div|temp\(16);
\clk_div|ALT_INV_temp\(18) <= NOT \clk_div|temp\(18);
\clk_div|ALT_INV_temp\(8) <= NOT \clk_div|temp\(8);
\clk_div|ALT_INV_temp\(24) <= NOT \clk_div|temp\(24);
\seqgen|ALT_INV_Add0~25_sumout\ <= NOT \seqgen|Add0~25_sumout\;
\seqgen|ALT_INV_cnt[5]~DUPLICATE_q\ <= NOT \seqgen|cnt[5]~DUPLICATE_q\;
\seqgen|ALT_INV_cnt[4]~DUPLICATE_q\ <= NOT \seqgen|cnt[4]~DUPLICATE_q\;
\seqgen|ALT_INV_cnt[7]~DUPLICATE_q\ <= NOT \seqgen|cnt[7]~DUPLICATE_q\;
\ALT_INV_enable~input_o\ <= NOT \enable~input_o\;
\seqcounter|counter1|j0|ALT_INV_sigQ~0_combout\ <= NOT \seqcounter|counter1|j0|sigQ~0_combout\;
\seqcounter|seq_detect|miniFSM2|ALT_INV_currentState.A0~q\ <= NOT \seqcounter|seq_detect|miniFSM2|currentState.A0~q\;
\seqcounter|seq_detect|miniFSM2|ALT_INV_currentState.A1~q\ <= NOT \seqcounter|seq_detect|miniFSM2|currentState.A1~q\;

-- Location: IOOBUF_X89_Y8_N39
\HEX0[0]~output\ : cyclonev_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false",
	shift_series_termination_control => "false")
-- pragma translate_on
PORT MAP (
	i => \hexdis0|Mux6~0_combout\,
	devoe => ww_devoe,
	o => ww_HEX0(0));

-- Location: IOOBUF_X89_Y11_N79
\HEX0[1]~output\ : cyclonev_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false",
	shift_series_termination_control => "false")
-- pragma translate_on
PORT MAP (
	i => \hexdis0|Mux5~0_combout\,
	devoe => ww_devoe,
	o => ww_HEX0(1));

-- Location: IOOBUF_X89_Y11_N96
\HEX0[2]~output\ : cyclonev_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false",
	shift_series_termination_control => "false")
-- pragma translate_on
PORT MAP (
	i => \hexdis0|Mux4~0_combout\,
	devoe => ww_devoe,
	o => ww_HEX0(2));

-- Location: IOOBUF_X89_Y4_N79
\HEX0[3]~output\ : cyclonev_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false",
	shift_series_termination_control => "false")
-- pragma translate_on
PORT MAP (
	i => \hexdis0|Mux3~0_combout\,
	devoe => ww_devoe,
	o => ww_HEX0(3));

-- Location: IOOBUF_X89_Y13_N56
\HEX0[4]~output\ : cyclonev_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false",
	shift_series_termination_control => "false")
-- pragma translate_on
PORT MAP (
	i => \hexdis0|Mux2~0_combout\,
	devoe => ww_devoe,
	o => ww_HEX0(4));

-- Location: IOOBUF_X89_Y13_N39
\HEX0[5]~output\ : cyclonev_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false",
	shift_series_termination_control => "false")
-- pragma translate_on
PORT MAP (
	i => \hexdis0|Mux1~0_combout\,
	devoe => ww_devoe,
	o => ww_HEX0(5));

-- Location: IOOBUF_X89_Y4_N96
\HEX0[6]~output\ : cyclonev_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false",
	shift_series_termination_control => "false")
-- pragma translate_on
PORT MAP (
	i => \hexdis0|Mux0~0_combout\,
	devoe => ww_devoe,
	o => ww_HEX0(6));

-- Location: IOOBUF_X89_Y20_N62
\HEX5[0]~output\ : cyclonev_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false",
	shift_series_termination_control => "false")
-- pragma translate_on
PORT MAP (
	i => \hexdis5|Mux6~0_combout\,
	devoe => ww_devoe,
	o => ww_HEX5(0));

-- Location: IOOBUF_X89_Y21_N56
\HEX5[1]~output\ : cyclonev_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false",
	shift_series_termination_control => "false")
-- pragma translate_on
PORT MAP (
	i => \hexdis5|Mux5~0_combout\,
	devoe => ww_devoe,
	o => ww_HEX5(1));

-- Location: IOOBUF_X89_Y25_N22
\HEX5[2]~output\ : cyclonev_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false",
	shift_series_termination_control => "false")
-- pragma translate_on
PORT MAP (
	i => \hexdis5|Mux4~0_combout\,
	devoe => ww_devoe,
	o => ww_HEX5(2));

-- Location: IOOBUF_X89_Y23_N22
\HEX5[3]~output\ : cyclonev_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false",
	shift_series_termination_control => "false")
-- pragma translate_on
PORT MAP (
	i => \hexdis5|Mux3~0_combout\,
	devoe => ww_devoe,
	o => ww_HEX5(3));

-- Location: IOOBUF_X89_Y9_N56
\HEX5[4]~output\ : cyclonev_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false",
	shift_series_termination_control => "false")
-- pragma translate_on
PORT MAP (
	i => \hexdis5|Mux2~0_combout\,
	devoe => ww_devoe,
	o => ww_HEX5(4));

-- Location: IOOBUF_X89_Y23_N5
\HEX5[5]~output\ : cyclonev_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false",
	shift_series_termination_control => "false")
-- pragma translate_on
PORT MAP (
	i => \hexdis5|Mux1~0_combout\,
	devoe => ww_devoe,
	o => ww_HEX5(5));

-- Location: IOOBUF_X89_Y9_N39
\HEX5[6]~output\ : cyclonev_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false",
	shift_series_termination_control => "false")
-- pragma translate_on
PORT MAP (
	i => \hexdis5|Mux0~0_combout\,
	devoe => ww_devoe,
	o => ww_HEX5(6));

-- Location: IOIBUF_X32_Y0_N1
\clk~input\ : cyclonev_io_ibuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	simulate_z_as => "z")
-- pragma translate_on
PORT MAP (
	i => ww_clk,
	o => \clk~input_o\);

-- Location: CLKCTRL_G6
\clk~inputCLKENA0\ : cyclonev_clkena
-- pragma translate_off
GENERIC MAP (
	clock_type => "global clock",
	disable_mode => "low",
	ena_register_mode => "always enabled",
	ena_register_power_up => "high",
	test_syn => "high")
-- pragma translate_on
PORT MAP (
	inclk => \clk~input_o\,
	outclk => \clk~inputCLKENA0_outclk\);

-- Location: LABCELL_X70_Y10_N30
\clk_div|Add0~73\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|Add0~73_sumout\ = SUM(( !\clk_div|temp\(0) ) + ( VCC ) + ( !VCC ))
-- \clk_div|Add0~74\ = CARRY(( !\clk_div|temp\(0) ) + ( VCC ) + ( !VCC ))

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000000000000000000000000000000000001111111100000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datad => \clk_div|ALT_INV_temp\(0),
	cin => GND,
	sumout => \clk_div|Add0~73_sumout\,
	cout => \clk_div|Add0~74\);

-- Location: LABCELL_X71_Y9_N24
\clk_div|temp[0]~10\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|temp[0]~10_combout\ = ( !\clk_div|Add0~73_sumout\ )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "1111111111111111111111111111111100000000000000000000000000000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataf => \clk_div|ALT_INV_Add0~73_sumout\,
	combout => \clk_div|temp[0]~10_combout\);

-- Location: IOIBUF_X36_Y0_N1
\reset~input\ : cyclonev_io_ibuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	simulate_z_as => "z")
-- pragma translate_on
PORT MAP (
	i => ww_reset,
	o => \reset~input_o\);

-- Location: IOIBUF_X2_Y0_N58
\enable~input\ : cyclonev_io_ibuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	simulate_z_as => "z")
-- pragma translate_on
PORT MAP (
	i => ww_enable,
	o => \enable~input_o\);

-- Location: FF_X71_Y9_N26
\clk_div|temp[0]\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk~inputCLKENA0_outclk\,
	d => \clk_div|temp[0]~10_combout\,
	clrn => \reset~input_o\,
	ena => \enable~input_o\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \clk_div|temp\(0));

-- Location: LABCELL_X70_Y10_N33
\clk_div|Add0~33\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|Add0~33_sumout\ = SUM(( !\clk_div|temp\(1) ) + ( VCC ) + ( \clk_div|Add0~74\ ))
-- \clk_div|Add0~34\ = CARRY(( !\clk_div|temp\(1) ) + ( VCC ) + ( \clk_div|Add0~74\ ))

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000000000000000000000000000000000001111111100000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datad => \clk_div|ALT_INV_temp\(1),
	cin => \clk_div|Add0~74\,
	sumout => \clk_div|Add0~33_sumout\,
	cout => \clk_div|Add0~34\);

-- Location: LABCELL_X70_Y10_N21
\clk_div|temp[1]~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|temp[1]~0_combout\ = ( !\clk_div|Add0~33_sumout\ )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "1111111111111111111111111111111100000000000000000000000000000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataf => \clk_div|ALT_INV_Add0~33_sumout\,
	combout => \clk_div|temp[1]~0_combout\);

-- Location: FF_X70_Y10_N23
\clk_div|temp[1]\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk~inputCLKENA0_outclk\,
	d => \clk_div|temp[1]~0_combout\,
	clrn => \reset~input_o\,
	ena => \enable~input_o\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \clk_div|temp\(1));

-- Location: LABCELL_X70_Y10_N36
\clk_div|Add0~37\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|Add0~37_sumout\ = SUM(( !\clk_div|temp\(2) ) + ( VCC ) + ( \clk_div|Add0~34\ ))
-- \clk_div|Add0~38\ = CARRY(( !\clk_div|temp\(2) ) + ( VCC ) + ( \clk_div|Add0~34\ ))

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000000000000000000000000000000000001111111100000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datad => \clk_div|ALT_INV_temp\(2),
	cin => \clk_div|Add0~34\,
	sumout => \clk_div|Add0~37_sumout\,
	cout => \clk_div|Add0~38\);

-- Location: LABCELL_X70_Y10_N0
\clk_div|temp[2]~1\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|temp[2]~1_combout\ = ( !\clk_div|Add0~37_sumout\ )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "1111111111111111000000000000000011111111111111110000000000000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datae => \clk_div|ALT_INV_Add0~37_sumout\,
	combout => \clk_div|temp[2]~1_combout\);

-- Location: FF_X70_Y10_N2
\clk_div|temp[2]\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk~inputCLKENA0_outclk\,
	d => \clk_div|temp[2]~1_combout\,
	clrn => \reset~input_o\,
	ena => \enable~input_o\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \clk_div|temp\(2));

-- Location: LABCELL_X70_Y10_N39
\clk_div|Add0~41\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|Add0~41_sumout\ = SUM(( !\clk_div|temp\(3) ) + ( VCC ) + ( \clk_div|Add0~38\ ))
-- \clk_div|Add0~42\ = CARRY(( !\clk_div|temp\(3) ) + ( VCC ) + ( \clk_div|Add0~38\ ))

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000000000000000000000000000000000001111111100000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datad => \clk_div|ALT_INV_temp\(3),
	cin => \clk_div|Add0~38\,
	sumout => \clk_div|Add0~41_sumout\,
	cout => \clk_div|Add0~42\);

-- Location: LABCELL_X70_Y10_N27
\clk_div|temp[3]~2\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|temp[3]~2_combout\ = ( !\clk_div|Add0~41_sumout\ )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "1111111111111111111111111111111100000000000000000000000000000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataf => \clk_div|ALT_INV_Add0~41_sumout\,
	combout => \clk_div|temp[3]~2_combout\);

-- Location: FF_X70_Y10_N29
\clk_div|temp[3]\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk~inputCLKENA0_outclk\,
	d => \clk_div|temp[3]~2_combout\,
	clrn => \reset~input_o\,
	ena => \enable~input_o\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \clk_div|temp\(3));

-- Location: LABCELL_X70_Y10_N42
\clk_div|Add0~45\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|Add0~45_sumout\ = SUM(( !\clk_div|temp\(4) ) + ( VCC ) + ( \clk_div|Add0~42\ ))
-- \clk_div|Add0~46\ = CARRY(( !\clk_div|temp\(4) ) + ( VCC ) + ( \clk_div|Add0~42\ ))

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000000000000000000000000000000000001100110011001100",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datab => \clk_div|ALT_INV_temp\(4),
	cin => \clk_div|Add0~42\,
	sumout => \clk_div|Add0~45_sumout\,
	cout => \clk_div|Add0~46\);

-- Location: LABCELL_X70_Y10_N6
\clk_div|temp[4]~3\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|temp[4]~3_combout\ = ( !\clk_div|Add0~45_sumout\ )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "1111111111111111111111111111111100000000000000000000000000000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataf => \clk_div|ALT_INV_Add0~45_sumout\,
	combout => \clk_div|temp[4]~3_combout\);

-- Location: FF_X70_Y10_N8
\clk_div|temp[4]\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk~inputCLKENA0_outclk\,
	d => \clk_div|temp[4]~3_combout\,
	clrn => \reset~input_o\,
	ena => \enable~input_o\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \clk_div|temp\(4));

-- Location: LABCELL_X70_Y10_N45
\clk_div|Add0~53\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|Add0~53_sumout\ = SUM(( !\clk_div|temp\(5) ) + ( VCC ) + ( \clk_div|Add0~46\ ))
-- \clk_div|Add0~54\ = CARRY(( !\clk_div|temp\(5) ) + ( VCC ) + ( \clk_div|Add0~46\ ))

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000000000000000000000000000000000001111111100000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datad => \clk_div|ALT_INV_temp\(5),
	cin => \clk_div|Add0~46\,
	sumout => \clk_div|Add0~53_sumout\,
	cout => \clk_div|Add0~54\);

-- Location: LABCELL_X70_Y10_N9
\clk_div|temp[5]~5\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|temp[5]~5_combout\ = !\clk_div|Add0~53_sumout\

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "1111111100000000111111110000000011111111000000001111111100000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datad => \clk_div|ALT_INV_Add0~53_sumout\,
	combout => \clk_div|temp[5]~5_combout\);

-- Location: FF_X70_Y10_N11
\clk_div|temp[5]\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk~inputCLKENA0_outclk\,
	d => \clk_div|temp[5]~5_combout\,
	clrn => \reset~input_o\,
	ena => \enable~input_o\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \clk_div|temp\(5));

-- Location: LABCELL_X70_Y10_N48
\clk_div|Add0~49\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|Add0~49_sumout\ = SUM(( !\clk_div|temp\(6) ) + ( VCC ) + ( \clk_div|Add0~54\ ))
-- \clk_div|Add0~50\ = CARRY(( !\clk_div|temp\(6) ) + ( VCC ) + ( \clk_div|Add0~54\ ))

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000000000000000000000000000000000001111000011110000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datac => \clk_div|ALT_INV_temp\(6),
	cin => \clk_div|Add0~54\,
	sumout => \clk_div|Add0~49_sumout\,
	cout => \clk_div|Add0~50\);

-- Location: LABCELL_X70_Y10_N24
\clk_div|temp[6]~4\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|temp[6]~4_combout\ = ( !\clk_div|Add0~49_sumout\ )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "1111111111111111111111111111111100000000000000000000000000000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataf => \clk_div|ALT_INV_Add0~49_sumout\,
	combout => \clk_div|temp[6]~4_combout\);

-- Location: FF_X70_Y10_N26
\clk_div|temp[6]\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk~inputCLKENA0_outclk\,
	d => \clk_div|temp[6]~4_combout\,
	clrn => \reset~input_o\,
	ena => \enable~input_o\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \clk_div|temp\(6));

-- Location: LABCELL_X70_Y10_N51
\clk_div|Add0~17\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|Add0~17_sumout\ = SUM(( \clk_div|temp\(7) ) + ( VCC ) + ( \clk_div|Add0~50\ ))
-- \clk_div|Add0~18\ = CARRY(( \clk_div|temp\(7) ) + ( VCC ) + ( \clk_div|Add0~50\ ))

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000000000000000000000000000000000000000000011111111",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datad => \clk_div|ALT_INV_temp\(7),
	cin => \clk_div|Add0~50\,
	sumout => \clk_div|Add0~17_sumout\,
	cout => \clk_div|Add0~18\);

-- Location: FF_X70_Y10_N53
\clk_div|temp[7]\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk~inputCLKENA0_outclk\,
	d => \clk_div|Add0~17_sumout\,
	clrn => \reset~input_o\,
	sclr => \clk_div|ALT_INV_en_out~0_combout\,
	ena => \enable~input_o\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \clk_div|temp\(7));

-- Location: LABCELL_X70_Y10_N54
\clk_div|Add0~5\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|Add0~5_sumout\ = SUM(( \clk_div|temp\(8) ) + ( VCC ) + ( \clk_div|Add0~18\ ))
-- \clk_div|Add0~6\ = CARRY(( \clk_div|temp\(8) ) + ( VCC ) + ( \clk_div|Add0~18\ ))

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000000000000000000000000000000000000000000011111111",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datad => \clk_div|ALT_INV_temp\(8),
	cin => \clk_div|Add0~18\,
	sumout => \clk_div|Add0~5_sumout\,
	cout => \clk_div|Add0~6\);

-- Location: FF_X70_Y10_N56
\clk_div|temp[8]\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk~inputCLKENA0_outclk\,
	d => \clk_div|Add0~5_sumout\,
	clrn => \reset~input_o\,
	sclr => \clk_div|ALT_INV_en_out~0_combout\,
	ena => \enable~input_o\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \clk_div|temp\(8));

-- Location: LABCELL_X70_Y10_N57
\clk_div|Add0~25\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|Add0~25_sumout\ = SUM(( \clk_div|temp\(9) ) + ( VCC ) + ( \clk_div|Add0~6\ ))
-- \clk_div|Add0~26\ = CARRY(( \clk_div|temp\(9) ) + ( VCC ) + ( \clk_div|Add0~6\ ))

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000000000000000000000000000000000000000000011111111",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datad => \clk_div|ALT_INV_temp\(9),
	cin => \clk_div|Add0~6\,
	sumout => \clk_div|Add0~25_sumout\,
	cout => \clk_div|Add0~26\);

-- Location: FF_X70_Y10_N59
\clk_div|temp[9]\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk~inputCLKENA0_outclk\,
	d => \clk_div|Add0~25_sumout\,
	clrn => \reset~input_o\,
	sclr => \clk_div|ALT_INV_en_out~0_combout\,
	ena => \enable~input_o\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \clk_div|temp\(9));

-- Location: LABCELL_X70_Y9_N0
\clk_div|Add0~29\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|Add0~29_sumout\ = SUM(( \clk_div|temp\(10) ) + ( VCC ) + ( \clk_div|Add0~26\ ))
-- \clk_div|Add0~30\ = CARRY(( \clk_div|temp\(10) ) + ( VCC ) + ( \clk_div|Add0~26\ ))

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000000000000000000000000000000000000000000011111111",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datad => \clk_div|ALT_INV_temp\(10),
	cin => \clk_div|Add0~26\,
	sumout => \clk_div|Add0~29_sumout\,
	cout => \clk_div|Add0~30\);

-- Location: FF_X70_Y9_N2
\clk_div|temp[10]\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk~inputCLKENA0_outclk\,
	d => \clk_div|Add0~29_sumout\,
	clrn => \reset~input_o\,
	sclr => \clk_div|ALT_INV_en_out~0_combout\,
	ena => \enable~input_o\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \clk_div|temp\(10));

-- Location: LABCELL_X70_Y9_N3
\clk_div|Add0~21\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|Add0~21_sumout\ = SUM(( \clk_div|temp\(11) ) + ( VCC ) + ( \clk_div|Add0~30\ ))
-- \clk_div|Add0~22\ = CARRY(( \clk_div|temp\(11) ) + ( VCC ) + ( \clk_div|Add0~30\ ))

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000000000000000000000000000000000000000000011111111",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datad => \clk_div|ALT_INV_temp\(11),
	cin => \clk_div|Add0~30\,
	sumout => \clk_div|Add0~21_sumout\,
	cout => \clk_div|Add0~22\);

-- Location: FF_X70_Y9_N5
\clk_div|temp[11]\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk~inputCLKENA0_outclk\,
	d => \clk_div|Add0~21_sumout\,
	clrn => \reset~input_o\,
	sclr => \clk_div|ALT_INV_en_out~0_combout\,
	ena => \enable~input_o\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \clk_div|temp\(11));

-- Location: LABCELL_X70_Y9_N6
\clk_div|Add0~81\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|Add0~81_sumout\ = SUM(( !\clk_div|temp\(12) ) + ( VCC ) + ( \clk_div|Add0~22\ ))
-- \clk_div|Add0~82\ = CARRY(( !\clk_div|temp\(12) ) + ( VCC ) + ( \clk_div|Add0~22\ ))

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000000000000000000000000000000000001111111100000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datad => \clk_div|ALT_INV_temp\(12),
	cin => \clk_div|Add0~22\,
	sumout => \clk_div|Add0~81_sumout\,
	cout => \clk_div|Add0~82\);

-- Location: LABCELL_X71_Y9_N6
\clk_div|temp[12]~12\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|temp[12]~12_combout\ = !\clk_div|Add0~81_sumout\

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "1111111100000000111111110000000011111111000000001111111100000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datad => \clk_div|ALT_INV_Add0~81_sumout\,
	combout => \clk_div|temp[12]~12_combout\);

-- Location: FF_X71_Y9_N8
\clk_div|temp[12]\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk~inputCLKENA0_outclk\,
	d => \clk_div|temp[12]~12_combout\,
	clrn => \reset~input_o\,
	ena => \enable~input_o\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \clk_div|temp\(12));

-- Location: LABCELL_X70_Y9_N9
\clk_div|Add0~85\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|Add0~85_sumout\ = SUM(( !\clk_div|temp\(13) ) + ( VCC ) + ( \clk_div|Add0~82\ ))
-- \clk_div|Add0~86\ = CARRY(( !\clk_div|temp\(13) ) + ( VCC ) + ( \clk_div|Add0~82\ ))

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000000000000000000000000000000000001111111100000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datad => \clk_div|ALT_INV_temp\(13),
	cin => \clk_div|Add0~82\,
	sumout => \clk_div|Add0~85_sumout\,
	cout => \clk_div|Add0~86\);

-- Location: LABCELL_X71_Y9_N15
\clk_div|temp[13]~13\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|temp[13]~13_combout\ = ( !\clk_div|Add0~85_sumout\ )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "1111111111111111111111111111111100000000000000000000000000000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataf => \clk_div|ALT_INV_Add0~85_sumout\,
	combout => \clk_div|temp[13]~13_combout\);

-- Location: FF_X71_Y9_N17
\clk_div|temp[13]\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk~inputCLKENA0_outclk\,
	d => \clk_div|temp[13]~13_combout\,
	clrn => \reset~input_o\,
	ena => \enable~input_o\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \clk_div|temp\(13));

-- Location: LABCELL_X70_Y9_N12
\clk_div|Add0~89\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|Add0~89_sumout\ = SUM(( !\clk_div|temp\(14) ) + ( VCC ) + ( \clk_div|Add0~86\ ))
-- \clk_div|Add0~90\ = CARRY(( !\clk_div|temp\(14) ) + ( VCC ) + ( \clk_div|Add0~86\ ))

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000000000000000000000000000000000001100110011001100",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datab => \clk_div|ALT_INV_temp\(14),
	cin => \clk_div|Add0~86\,
	sumout => \clk_div|Add0~89_sumout\,
	cout => \clk_div|Add0~90\);

-- Location: LABCELL_X71_Y9_N18
\clk_div|temp[14]~14\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|temp[14]~14_combout\ = ( !\clk_div|Add0~89_sumout\ )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "1111111111111111111111111111111100000000000000000000000000000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataf => \clk_div|ALT_INV_Add0~89_sumout\,
	combout => \clk_div|temp[14]~14_combout\);

-- Location: FF_X71_Y9_N20
\clk_div|temp[14]\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk~inputCLKENA0_outclk\,
	d => \clk_div|temp[14]~14_combout\,
	clrn => \reset~input_o\,
	ena => \enable~input_o\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \clk_div|temp\(14));

-- Location: LABCELL_X70_Y9_N15
\clk_div|Add0~93\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|Add0~93_sumout\ = SUM(( !\clk_div|temp\(15) ) + ( VCC ) + ( \clk_div|Add0~90\ ))
-- \clk_div|Add0~94\ = CARRY(( !\clk_div|temp\(15) ) + ( VCC ) + ( \clk_div|Add0~90\ ))

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000000000000000000000000000000000001111111100000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datad => \clk_div|ALT_INV_temp\(15),
	cin => \clk_div|Add0~90\,
	sumout => \clk_div|Add0~93_sumout\,
	cout => \clk_div|Add0~94\);

-- Location: LABCELL_X71_Y9_N9
\clk_div|temp[15]~15\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|temp[15]~15_combout\ = ( !\clk_div|Add0~93_sumout\ )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "1111111111111111111111111111111100000000000000000000000000000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataf => \clk_div|ALT_INV_Add0~93_sumout\,
	combout => \clk_div|temp[15]~15_combout\);

-- Location: FF_X71_Y9_N11
\clk_div|temp[15]\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk~inputCLKENA0_outclk\,
	d => \clk_div|temp[15]~15_combout\,
	clrn => \reset~input_o\,
	ena => \enable~input_o\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \clk_div|temp\(15));

-- Location: LABCELL_X70_Y9_N18
\clk_div|Add0~13\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|Add0~13_sumout\ = SUM(( \clk_div|temp\(16) ) + ( VCC ) + ( \clk_div|Add0~94\ ))
-- \clk_div|Add0~14\ = CARRY(( \clk_div|temp\(16) ) + ( VCC ) + ( \clk_div|Add0~94\ ))

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000000000000000000000000000000000000000111100001111",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datac => \clk_div|ALT_INV_temp\(16),
	cin => \clk_div|Add0~94\,
	sumout => \clk_div|Add0~13_sumout\,
	cout => \clk_div|Add0~14\);

-- Location: FF_X70_Y9_N20
\clk_div|temp[16]\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk~inputCLKENA0_outclk\,
	d => \clk_div|Add0~13_sumout\,
	clrn => \reset~input_o\,
	sclr => \clk_div|ALT_INV_en_out~0_combout\,
	ena => \enable~input_o\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \clk_div|temp\(16));

-- Location: LABCELL_X70_Y9_N21
\clk_div|Add0~101\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|Add0~101_sumout\ = SUM(( !\clk_div|temp\(17) ) + ( VCC ) + ( \clk_div|Add0~14\ ))
-- \clk_div|Add0~102\ = CARRY(( !\clk_div|temp\(17) ) + ( VCC ) + ( \clk_div|Add0~14\ ))

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000000000000000000000000000000000001010101010101010",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \clk_div|ALT_INV_temp\(17),
	cin => \clk_div|Add0~14\,
	sumout => \clk_div|Add0~101_sumout\,
	cout => \clk_div|Add0~102\);

-- Location: LABCELL_X71_Y9_N45
\clk_div|temp[17]~17\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|temp[17]~17_combout\ = ( !\clk_div|Add0~101_sumout\ )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "1111111111111111000000000000000011111111111111110000000000000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datae => \clk_div|ALT_INV_Add0~101_sumout\,
	combout => \clk_div|temp[17]~17_combout\);

-- Location: FF_X71_Y9_N47
\clk_div|temp[17]\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk~inputCLKENA0_outclk\,
	d => \clk_div|temp[17]~17_combout\,
	clrn => \reset~input_o\,
	ena => \enable~input_o\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \clk_div|temp\(17));

-- Location: LABCELL_X70_Y9_N24
\clk_div|Add0~9\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|Add0~9_sumout\ = SUM(( \clk_div|temp\(18) ) + ( VCC ) + ( \clk_div|Add0~102\ ))
-- \clk_div|Add0~10\ = CARRY(( \clk_div|temp\(18) ) + ( VCC ) + ( \clk_div|Add0~102\ ))

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000000000000000000000000000000000000000111100001111",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datac => \clk_div|ALT_INV_temp\(18),
	cin => \clk_div|Add0~102\,
	sumout => \clk_div|Add0~9_sumout\,
	cout => \clk_div|Add0~10\);

-- Location: FF_X70_Y9_N26
\clk_div|temp[18]\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk~inputCLKENA0_outclk\,
	d => \clk_div|Add0~9_sumout\,
	clrn => \reset~input_o\,
	sclr => \clk_div|ALT_INV_en_out~0_combout\,
	ena => \enable~input_o\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \clk_div|temp\(18));

-- Location: LABCELL_X70_Y9_N27
\clk_div|Add0~97\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|Add0~97_sumout\ = SUM(( !\clk_div|temp\(19) ) + ( VCC ) + ( \clk_div|Add0~10\ ))
-- \clk_div|Add0~98\ = CARRY(( !\clk_div|temp\(19) ) + ( VCC ) + ( \clk_div|Add0~10\ ))

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000000000000000000000000000000000001111000011110000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datac => \clk_div|ALT_INV_temp\(19),
	cin => \clk_div|Add0~10\,
	sumout => \clk_div|Add0~97_sumout\,
	cout => \clk_div|Add0~98\);

-- Location: LABCELL_X71_Y9_N12
\clk_div|temp[19]~16\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|temp[19]~16_combout\ = ( !\clk_div|Add0~97_sumout\ )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "1111111111111111111111111111111100000000000000000000000000000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataf => \clk_div|ALT_INV_Add0~97_sumout\,
	combout => \clk_div|temp[19]~16_combout\);

-- Location: FF_X71_Y9_N14
\clk_div|temp[19]\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk~inputCLKENA0_outclk\,
	d => \clk_div|temp[19]~16_combout\,
	clrn => \reset~input_o\,
	ena => \enable~input_o\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \clk_div|temp\(19));

-- Location: LABCELL_X71_Y9_N54
\clk_div|en_out~4\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|en_out~4_combout\ = ( \clk_div|temp\(17) & ( \clk_div|temp\(13) & ( (\clk_div|temp\(12) & (\clk_div|temp\(15) & (\clk_div|temp\(14) & \clk_div|temp\(19)))) ) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000000000000000000000000000000000000000000000000001",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \clk_div|ALT_INV_temp\(12),
	datab => \clk_div|ALT_INV_temp\(15),
	datac => \clk_div|ALT_INV_temp\(14),
	datad => \clk_div|ALT_INV_temp\(19),
	datae => \clk_div|ALT_INV_temp\(17),
	dataf => \clk_div|ALT_INV_temp\(13),
	combout => \clk_div|en_out~4_combout\);

-- Location: LABCELL_X70_Y10_N12
\clk_div|en_out~2\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|en_out~2_combout\ = ( \clk_div|temp\(3) & ( \clk_div|temp\(2) & ( (\clk_div|temp\(1) & (\clk_div|temp\(5) & (\clk_div|temp\(6) & \clk_div|temp\(4)))) ) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000000000000000000000000000000000000000000000000001",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \clk_div|ALT_INV_temp\(1),
	datab => \clk_div|ALT_INV_temp\(5),
	datac => \clk_div|ALT_INV_temp\(6),
	datad => \clk_div|ALT_INV_temp\(4),
	datae => \clk_div|ALT_INV_temp\(3),
	dataf => \clk_div|ALT_INV_temp\(2),
	combout => \clk_div|en_out~2_combout\);

-- Location: LABCELL_X70_Y9_N30
\clk_div|Add0~57\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|Add0~57_sumout\ = SUM(( !\clk_div|temp\(20) ) + ( VCC ) + ( \clk_div|Add0~98\ ))
-- \clk_div|Add0~58\ = CARRY(( !\clk_div|temp\(20) ) + ( VCC ) + ( \clk_div|Add0~98\ ))

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000000000000000000000000000000000001111000011110000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datac => \clk_div|ALT_INV_temp\(20),
	cin => \clk_div|Add0~98\,
	sumout => \clk_div|Add0~57_sumout\,
	cout => \clk_div|Add0~58\);

-- Location: LABCELL_X70_Y9_N51
\clk_div|temp[20]~6\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|temp[20]~6_combout\ = ( !\clk_div|Add0~57_sumout\ )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "1111111111111111111111111111111100000000000000000000000000000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataf => \clk_div|ALT_INV_Add0~57_sumout\,
	combout => \clk_div|temp[20]~6_combout\);

-- Location: FF_X70_Y9_N53
\clk_div|temp[20]\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk~inputCLKENA0_outclk\,
	d => \clk_div|temp[20]~6_combout\,
	clrn => \reset~input_o\,
	ena => \enable~input_o\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \clk_div|temp\(20));

-- Location: LABCELL_X70_Y9_N33
\clk_div|Add0~61\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|Add0~61_sumout\ = SUM(( !\clk_div|temp\(21) ) + ( VCC ) + ( \clk_div|Add0~58\ ))
-- \clk_div|Add0~62\ = CARRY(( !\clk_div|temp\(21) ) + ( VCC ) + ( \clk_div|Add0~58\ ))

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000000000000000000000000000000000001010101010101010",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \clk_div|ALT_INV_temp\(21),
	cin => \clk_div|Add0~58\,
	sumout => \clk_div|Add0~61_sumout\,
	cout => \clk_div|Add0~62\);

-- Location: LABCELL_X71_Y9_N39
\clk_div|temp[21]~7\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|temp[21]~7_combout\ = ( !\clk_div|Add0~61_sumout\ )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "1111111111111111000000000000000011111111111111110000000000000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datae => \clk_div|ALT_INV_Add0~61_sumout\,
	combout => \clk_div|temp[21]~7_combout\);

-- Location: FF_X71_Y9_N41
\clk_div|temp[21]\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk~inputCLKENA0_outclk\,
	d => \clk_div|temp[21]~7_combout\,
	clrn => \reset~input_o\,
	ena => \enable~input_o\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \clk_div|temp\(21));

-- Location: LABCELL_X70_Y9_N36
\clk_div|Add0~65\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|Add0~65_sumout\ = SUM(( !\clk_div|temp\(22) ) + ( VCC ) + ( \clk_div|Add0~62\ ))
-- \clk_div|Add0~66\ = CARRY(( !\clk_div|temp\(22) ) + ( VCC ) + ( \clk_div|Add0~62\ ))

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000000000000000000000000000000000001111000011110000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datac => \clk_div|ALT_INV_temp\(22),
	cin => \clk_div|Add0~62\,
	sumout => \clk_div|Add0~65_sumout\,
	cout => \clk_div|Add0~66\);

-- Location: LABCELL_X70_Y9_N48
\clk_div|temp[22]~8\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|temp[22]~8_combout\ = !\clk_div|Add0~65_sumout\

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "1111111100000000111111110000000011111111000000001111111100000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datad => \clk_div|ALT_INV_Add0~65_sumout\,
	combout => \clk_div|temp[22]~8_combout\);

-- Location: FF_X70_Y9_N50
\clk_div|temp[22]\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk~inputCLKENA0_outclk\,
	d => \clk_div|temp[22]~8_combout\,
	clrn => \reset~input_o\,
	ena => \enable~input_o\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \clk_div|temp\(22));

-- Location: LABCELL_X70_Y9_N39
\clk_div|Add0~69\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|Add0~69_sumout\ = SUM(( !\clk_div|temp\(23) ) + ( VCC ) + ( \clk_div|Add0~66\ ))
-- \clk_div|Add0~70\ = CARRY(( !\clk_div|temp\(23) ) + ( VCC ) + ( \clk_div|Add0~66\ ))

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000000000000000000000000000000000001111111100000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datad => \clk_div|ALT_INV_temp\(23),
	cin => \clk_div|Add0~66\,
	sumout => \clk_div|Add0~69_sumout\,
	cout => \clk_div|Add0~70\);

-- Location: LABCELL_X70_Y9_N54
\clk_div|temp[23]~9\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|temp[23]~9_combout\ = ( !\clk_div|Add0~69_sumout\ )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "1111111111111111111111111111111100000000000000000000000000000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataf => \clk_div|ALT_INV_Add0~69_sumout\,
	combout => \clk_div|temp[23]~9_combout\);

-- Location: FF_X70_Y9_N56
\clk_div|temp[23]\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk~inputCLKENA0_outclk\,
	d => \clk_div|temp[23]~9_combout\,
	clrn => \reset~input_o\,
	ena => \enable~input_o\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \clk_div|temp\(23));

-- Location: LABCELL_X70_Y9_N42
\clk_div|Add0~1\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|Add0~1_sumout\ = SUM(( \clk_div|temp\(24) ) + ( VCC ) + ( \clk_div|Add0~70\ ))
-- \clk_div|Add0~2\ = CARRY(( \clk_div|temp\(24) ) + ( VCC ) + ( \clk_div|Add0~70\ ))

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000000000000000000000000000000000000011001100110011",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datab => \clk_div|ALT_INV_temp\(24),
	cin => \clk_div|Add0~70\,
	sumout => \clk_div|Add0~1_sumout\,
	cout => \clk_div|Add0~2\);

-- Location: FF_X70_Y9_N44
\clk_div|temp[24]\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk~inputCLKENA0_outclk\,
	d => \clk_div|Add0~1_sumout\,
	clrn => \reset~input_o\,
	sclr => \clk_div|ALT_INV_en_out~0_combout\,
	ena => \enable~input_o\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \clk_div|temp\(24));

-- Location: LABCELL_X71_Y9_N0
\clk_div|en_out~1\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|en_out~1_combout\ = ( !\clk_div|temp\(16) & ( !\clk_div|temp\(11) & ( (!\clk_div|temp\(10) & (!\clk_div|temp\(9) & (!\clk_div|temp\(18) & !\clk_div|temp\(7)))) ) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "1000000000000000000000000000000000000000000000000000000000000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \clk_div|ALT_INV_temp\(10),
	datab => \clk_div|ALT_INV_temp\(9),
	datac => \clk_div|ALT_INV_temp\(18),
	datad => \clk_div|ALT_INV_temp\(7),
	datae => \clk_div|ALT_INV_temp\(16),
	dataf => \clk_div|ALT_INV_temp\(11),
	combout => \clk_div|en_out~1_combout\);

-- Location: LABCELL_X70_Y9_N45
\clk_div|Add0~77\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|Add0~77_sumout\ = SUM(( !\clk_div|temp\(25) ) + ( VCC ) + ( \clk_div|Add0~2\ ))

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000000000000000000000000000000000001111000011110000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datac => \clk_div|ALT_INV_temp\(25),
	cin => \clk_div|Add0~2\,
	sumout => \clk_div|Add0~77_sumout\);

-- Location: LABCELL_X70_Y9_N57
\clk_div|temp[25]~11\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|temp[25]~11_combout\ = !\clk_div|Add0~77_sumout\

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "1111111100000000111111110000000011111111000000001111111100000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datad => \clk_div|ALT_INV_Add0~77_sumout\,
	combout => \clk_div|temp[25]~11_combout\);

-- Location: FF_X70_Y9_N59
\clk_div|temp[25]\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk~inputCLKENA0_outclk\,
	d => \clk_div|temp[25]~11_combout\,
	clrn => \reset~input_o\,
	ena => \enable~input_o\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \clk_div|temp\(25));

-- Location: LABCELL_X71_Y9_N48
\clk_div|en_out~3\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|en_out~3_combout\ = ( \clk_div|temp\(22) & ( \clk_div|temp\(23) & ( (\clk_div|temp\(0) & (\clk_div|temp\(21) & (\clk_div|temp\(25) & \clk_div|temp\(20)))) ) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000000000000000000000000000000000000000000000000001",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \clk_div|ALT_INV_temp\(0),
	datab => \clk_div|ALT_INV_temp\(21),
	datac => \clk_div|ALT_INV_temp\(25),
	datad => \clk_div|ALT_INV_temp\(20),
	datae => \clk_div|ALT_INV_temp\(22),
	dataf => \clk_div|ALT_INV_temp\(23),
	combout => \clk_div|en_out~3_combout\);

-- Location: LABCELL_X71_Y9_N30
\clk_div|en_out~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \clk_div|en_out~0_combout\ = LCELL(( \clk_div|en_out~1_combout\ & ( \clk_div|en_out~3_combout\ & ( (!\clk_div|en_out~4_combout\) # (((!\clk_div|en_out~2_combout\) # (\clk_div|temp\(24))) # (\clk_div|temp\(8))) ) ) ) # ( !\clk_div|en_out~1_combout\ & ( 
-- \clk_div|en_out~3_combout\ ) ) # ( \clk_div|en_out~1_combout\ & ( !\clk_div|en_out~3_combout\ ) ) # ( !\clk_div|en_out~1_combout\ & ( !\clk_div|en_out~3_combout\ ) ))

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "1111111111111111111111111111111111111111111111111111101111111111",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \clk_div|ALT_INV_en_out~4_combout\,
	datab => \clk_div|ALT_INV_temp\(8),
	datac => \clk_div|ALT_INV_en_out~2_combout\,
	datad => \clk_div|ALT_INV_temp\(24),
	datae => \clk_div|ALT_INV_en_out~1_combout\,
	dataf => \clk_div|ALT_INV_en_out~3_combout\,
	combout => \clk_div|en_out~0_combout\);

-- Location: FF_X74_Y8_N37
\seqgen|cnt[5]~DUPLICATE\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk_div|ALT_INV_en_out~0_combout\,
	asdata => \seqgen|Add0~17_sumout\,
	clrn => \reset~input_o\,
	sload => VCC,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \seqgen|cnt[5]~DUPLICATE_q\);

-- Location: LABCELL_X73_Y8_N30
\seqgen|cnt[0]~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \seqgen|cnt[0]~0_combout\ = !\seqgen|cnt\(0)

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "1100110011001100110011001100110011001100110011001100110011001100",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datab => \seqgen|ALT_INV_cnt\(0),
	combout => \seqgen|cnt[0]~0_combout\);

-- Location: FF_X74_Y8_N14
\seqgen|cnt[0]\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk_div|ALT_INV_en_out~0_combout\,
	asdata => \seqgen|cnt[0]~0_combout\,
	clrn => \reset~input_o\,
	sload => VCC,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \seqgen|cnt\(0));

-- Location: LABCELL_X73_Y8_N0
\seqgen|Add0~25\ : cyclonev_lcell_comb
-- Equation(s):
-- \seqgen|Add0~25_sumout\ = SUM(( \seqgen|cnt\(0) ) + ( \seqgen|cnt\(1) ) + ( !VCC ))
-- \seqgen|Add0~26\ = CARRY(( \seqgen|cnt\(0) ) + ( \seqgen|cnt\(1) ) + ( !VCC ))

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000111111110000000000000000000000000011001100110011",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datab => \seqgen|ALT_INV_cnt\(0),
	dataf => \seqgen|ALT_INV_cnt\(1),
	cin => GND,
	sumout => \seqgen|Add0~25_sumout\,
	cout => \seqgen|Add0~26\);

-- Location: LABCELL_X73_Y8_N39
\seqgen|cnt[1]~feeder\ : cyclonev_lcell_comb
-- Equation(s):
-- \seqgen|cnt[1]~feeder_combout\ = \seqgen|Add0~25_sumout\

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0101010101010101010101010101010101010101010101010101010101010101",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \seqgen|ALT_INV_Add0~25_sumout\,
	combout => \seqgen|cnt[1]~feeder_combout\);

-- Location: FF_X73_Y8_N41
\seqgen|cnt[1]\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk_div|ALT_INV_en_out~0_combout\,
	d => \seqgen|cnt[1]~feeder_combout\,
	clrn => \reset~input_o\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \seqgen|cnt\(1));

-- Location: LABCELL_X73_Y8_N3
\seqgen|Add0~5\ : cyclonev_lcell_comb
-- Equation(s):
-- \seqgen|Add0~5_sumout\ = SUM(( \seqgen|cnt\(2) ) + ( GND ) + ( \seqgen|Add0~26\ ))
-- \seqgen|Add0~6\ = CARRY(( \seqgen|cnt\(2) ) + ( GND ) + ( \seqgen|Add0~26\ ))

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000111111111111111100000000000000000000000011111111",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datad => \seqgen|ALT_INV_cnt\(2),
	cin => \seqgen|Add0~26\,
	sumout => \seqgen|Add0~5_sumout\,
	cout => \seqgen|Add0~6\);

-- Location: FF_X74_Y8_N5
\seqgen|cnt[2]\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk_div|ALT_INV_en_out~0_combout\,
	asdata => \seqgen|Add0~5_sumout\,
	clrn => \reset~input_o\,
	sload => VCC,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \seqgen|cnt\(2));

-- Location: LABCELL_X73_Y8_N6
\seqgen|Add0~21\ : cyclonev_lcell_comb
-- Equation(s):
-- \seqgen|Add0~21_sumout\ = SUM(( \seqgen|cnt\(3) ) + ( GND ) + ( \seqgen|Add0~6\ ))
-- \seqgen|Add0~22\ = CARRY(( \seqgen|cnt\(3) ) + ( GND ) + ( \seqgen|Add0~6\ ))

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000111111111111111100000000000000000011001100110011",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datab => \seqgen|ALT_INV_cnt\(3),
	cin => \seqgen|Add0~6\,
	sumout => \seqgen|Add0~21_sumout\,
	cout => \seqgen|Add0~22\);

-- Location: FF_X74_Y8_N20
\seqgen|cnt[3]\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk_div|ALT_INV_en_out~0_combout\,
	asdata => \seqgen|Add0~21_sumout\,
	clrn => \reset~input_o\,
	sload => VCC,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \seqgen|cnt\(3));

-- Location: LABCELL_X73_Y8_N9
\seqgen|Add0~13\ : cyclonev_lcell_comb
-- Equation(s):
-- \seqgen|Add0~13_sumout\ = SUM(( \seqgen|cnt[4]~DUPLICATE_q\ ) + ( GND ) + ( \seqgen|Add0~22\ ))
-- \seqgen|Add0~14\ = CARRY(( \seqgen|cnt[4]~DUPLICATE_q\ ) + ( GND ) + ( \seqgen|Add0~22\ ))

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000111111111111111100000000000000000000000011111111",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datad => \seqgen|ALT_INV_cnt[4]~DUPLICATE_q\,
	cin => \seqgen|Add0~22\,
	sumout => \seqgen|Add0~13_sumout\,
	cout => \seqgen|Add0~14\);

-- Location: FF_X74_Y8_N23
\seqgen|cnt[4]~DUPLICATE\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk_div|ALT_INV_en_out~0_combout\,
	asdata => \seqgen|Add0~13_sumout\,
	clrn => \reset~input_o\,
	sload => VCC,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \seqgen|cnt[4]~DUPLICATE_q\);

-- Location: LABCELL_X73_Y8_N12
\seqgen|Add0~17\ : cyclonev_lcell_comb
-- Equation(s):
-- \seqgen|Add0~17_sumout\ = SUM(( \seqgen|cnt[5]~DUPLICATE_q\ ) + ( GND ) + ( \seqgen|Add0~14\ ))
-- \seqgen|Add0~18\ = CARRY(( \seqgen|cnt[5]~DUPLICATE_q\ ) + ( GND ) + ( \seqgen|Add0~14\ ))

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000111111111111111100000000000000000000000011111111",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datad => \seqgen|ALT_INV_cnt[5]~DUPLICATE_q\,
	cin => \seqgen|Add0~14\,
	sumout => \seqgen|Add0~17_sumout\,
	cout => \seqgen|Add0~18\);

-- Location: FF_X74_Y8_N38
\seqgen|cnt[5]\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk_div|ALT_INV_en_out~0_combout\,
	asdata => \seqgen|Add0~17_sumout\,
	clrn => \reset~input_o\,
	sload => VCC,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \seqgen|cnt\(5));

-- Location: FF_X74_Y8_N22
\seqgen|cnt[4]\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk_div|ALT_INV_en_out~0_combout\,
	asdata => \seqgen|Add0~13_sumout\,
	clrn => \reset~input_o\,
	sload => VCC,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \seqgen|cnt\(4));

-- Location: LABCELL_X73_Y8_N15
\seqgen|Add0~9\ : cyclonev_lcell_comb
-- Equation(s):
-- \seqgen|Add0~9_sumout\ = SUM(( \seqgen|cnt\(6) ) + ( GND ) + ( \seqgen|Add0~18\ ))
-- \seqgen|Add0~10\ = CARRY(( \seqgen|cnt\(6) ) + ( GND ) + ( \seqgen|Add0~18\ ))

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000111111111111111100000000000000000000000011111111",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datad => \seqgen|ALT_INV_cnt\(6),
	cin => \seqgen|Add0~18\,
	sumout => \seqgen|Add0~9_sumout\,
	cout => \seqgen|Add0~10\);

-- Location: FF_X74_Y8_N17
\seqgen|cnt[6]\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk_div|ALT_INV_en_out~0_combout\,
	asdata => \seqgen|Add0~9_sumout\,
	clrn => \reset~input_o\,
	sload => VCC,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \seqgen|cnt\(6));

-- Location: LABCELL_X73_Y8_N18
\seqgen|Add0~1\ : cyclonev_lcell_comb
-- Equation(s):
-- \seqgen|Add0~1_sumout\ = SUM(( \seqgen|cnt[7]~DUPLICATE_q\ ) + ( GND ) + ( \seqgen|Add0~10\ ))

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000111111111111111100000000000000000000000011111111",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datad => \seqgen|ALT_INV_cnt[7]~DUPLICATE_q\,
	cin => \seqgen|Add0~10\,
	sumout => \seqgen|Add0~1_sumout\);

-- Location: FF_X74_Y8_N50
\seqgen|cnt[7]~DUPLICATE\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk_div|ALT_INV_en_out~0_combout\,
	asdata => \seqgen|Add0~1_sumout\,
	clrn => \reset~input_o\,
	sload => VCC,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \seqgen|cnt[7]~DUPLICATE_q\);

-- Location: LABCELL_X74_Y8_N0
\seqgen|Mux0~1\ : cyclonev_lcell_comb
-- Equation(s):
-- \seqgen|Mux0~1_combout\ = ( \seqgen|cnt\(2) & ( \seqgen|cnt\(0) & ( (!\seqgen|cnt\(5) & (!\seqgen|cnt[7]~DUPLICATE_q\ & ((\seqgen|cnt\(6)) # (\seqgen|cnt\(4))))) # (\seqgen|cnt\(5) & ((!\seqgen|cnt\(4) & (\seqgen|cnt[7]~DUPLICATE_q\ & !\seqgen|cnt\(6))) # 
-- (\seqgen|cnt\(4) & ((\seqgen|cnt\(6)))))) ) ) ) # ( !\seqgen|cnt\(2) & ( \seqgen|cnt\(0) & ( (!\seqgen|cnt\(6) & ((!\seqgen|cnt\(4) $ (\seqgen|cnt[7]~DUPLICATE_q\)))) # (\seqgen|cnt\(6) & (!\seqgen|cnt\(5) $ (((!\seqgen|cnt\(4)) # 
-- (\seqgen|cnt[7]~DUPLICATE_q\))))) ) ) ) # ( \seqgen|cnt\(2) & ( !\seqgen|cnt\(0) & ( (!\seqgen|cnt\(6) & (!\seqgen|cnt[7]~DUPLICATE_q\ & (!\seqgen|cnt\(5) $ (\seqgen|cnt\(4))))) # (\seqgen|cnt\(6) & ((!\seqgen|cnt\(5) & ((\seqgen|cnt[7]~DUPLICATE_q\))) # 
-- (\seqgen|cnt\(5) & (!\seqgen|cnt\(4))))) ) ) ) # ( !\seqgen|cnt\(2) & ( !\seqgen|cnt\(0) & ( (!\seqgen|cnt[7]~DUPLICATE_q\ & (!\seqgen|cnt\(5) & ((!\seqgen|cnt\(6))))) # (\seqgen|cnt[7]~DUPLICATE_q\ & ((!\seqgen|cnt\(4) & (\seqgen|cnt\(5) & 
-- !\seqgen|cnt\(6))) # (\seqgen|cnt\(4) & ((\seqgen|cnt\(6)))))) ) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "1010010000000011100100000100111011000011011001010010010010110001",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \seqgen|ALT_INV_cnt\(5),
	datab => \seqgen|ALT_INV_cnt\(4),
	datac => \seqgen|ALT_INV_cnt[7]~DUPLICATE_q\,
	datad => \seqgen|ALT_INV_cnt\(6),
	datae => \seqgen|ALT_INV_cnt\(2),
	dataf => \seqgen|ALT_INV_cnt\(0),
	combout => \seqgen|Mux0~1_combout\);

-- Location: LABCELL_X74_Y8_N51
\seqgen|Mux0~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \seqgen|Mux0~0_combout\ = ( \seqgen|cnt[4]~DUPLICATE_q\ & ( \seqgen|cnt[7]~DUPLICATE_q\ & ( (!\seqgen|cnt\(5) & ((!\seqgen|cnt\(0) & ((!\seqgen|cnt\(2)))) # (\seqgen|cnt\(0) & (!\seqgen|cnt\(6) & \seqgen|cnt\(2))))) # (\seqgen|cnt\(5) & (!\seqgen|cnt\(2) 
-- $ (((!\seqgen|cnt\(0)) # (\seqgen|cnt\(6)))))) ) ) ) # ( !\seqgen|cnt[4]~DUPLICATE_q\ & ( \seqgen|cnt[7]~DUPLICATE_q\ & ( (!\seqgen|cnt\(0) & ((!\seqgen|cnt\(2) & (!\seqgen|cnt\(5))) # (\seqgen|cnt\(2) & ((!\seqgen|cnt\(6)))))) # (\seqgen|cnt\(0) & 
-- (!\seqgen|cnt\(6) $ (((!\seqgen|cnt\(5) & \seqgen|cnt\(2)))))) ) ) ) # ( \seqgen|cnt[4]~DUPLICATE_q\ & ( !\seqgen|cnt[7]~DUPLICATE_q\ & ( (!\seqgen|cnt\(0) & ((!\seqgen|cnt\(6) & (\seqgen|cnt\(5))) # (\seqgen|cnt\(6) & ((!\seqgen|cnt\(2)))))) # 
-- (\seqgen|cnt\(0) & (!\seqgen|cnt\(5) $ ((\seqgen|cnt\(6))))) ) ) ) # ( !\seqgen|cnt[4]~DUPLICATE_q\ & ( !\seqgen|cnt[7]~DUPLICATE_q\ & ( (!\seqgen|cnt\(6) & (\seqgen|cnt\(2) & (!\seqgen|cnt\(5) $ (!\seqgen|cnt\(0))))) # (\seqgen|cnt\(6) & 
-- (((!\seqgen|cnt\(0) & !\seqgen|cnt\(2))))) ) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000110001100000011011010110000110111000110100101001100001100101",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \seqgen|ALT_INV_cnt\(5),
	datab => \seqgen|ALT_INV_cnt\(0),
	datac => \seqgen|ALT_INV_cnt\(6),
	datad => \seqgen|ALT_INV_cnt\(2),
	datae => \seqgen|ALT_INV_cnt[4]~DUPLICATE_q\,
	dataf => \seqgen|ALT_INV_cnt[7]~DUPLICATE_q\,
	combout => \seqgen|Mux0~0_combout\);

-- Location: LABCELL_X74_Y8_N39
\seqgen|Mux0~2\ : cyclonev_lcell_comb
-- Equation(s):
-- \seqgen|Mux0~2_combout\ = ( \seqgen|cnt\(2) & ( \seqgen|cnt[5]~DUPLICATE_q\ & ( (!\seqgen|cnt[4]~DUPLICATE_q\ & ((!\seqgen|cnt\(6)) # ((!\seqgen|cnt[7]~DUPLICATE_q\)))) # (\seqgen|cnt[4]~DUPLICATE_q\ & ((!\seqgen|cnt[7]~DUPLICATE_q\ & (!\seqgen|cnt\(6))) 
-- # (\seqgen|cnt[7]~DUPLICATE_q\ & ((\seqgen|cnt\(0)))))) ) ) ) # ( !\seqgen|cnt\(2) & ( \seqgen|cnt[5]~DUPLICATE_q\ & ( (!\seqgen|cnt[4]~DUPLICATE_q\ & (!\seqgen|cnt\(6) & (!\seqgen|cnt\(0) $ (\seqgen|cnt[7]~DUPLICATE_q\)))) # (\seqgen|cnt[4]~DUPLICATE_q\ 
-- & (!\seqgen|cnt\(0) & (!\seqgen|cnt\(6) $ (\seqgen|cnt[7]~DUPLICATE_q\)))) ) ) ) # ( \seqgen|cnt\(2) & ( !\seqgen|cnt[5]~DUPLICATE_q\ & ( (!\seqgen|cnt\(0) & (((\seqgen|cnt\(6) & \seqgen|cnt[7]~DUPLICATE_q\)))) # (\seqgen|cnt\(0) & 
-- (!\seqgen|cnt[4]~DUPLICATE_q\ $ (((\seqgen|cnt[7]~DUPLICATE_q\) # (\seqgen|cnt\(6)))))) ) ) ) # ( !\seqgen|cnt\(2) & ( !\seqgen|cnt[5]~DUPLICATE_q\ & ( (!\seqgen|cnt\(6) & (\seqgen|cnt\(0) & (!\seqgen|cnt[4]~DUPLICATE_q\ $ 
-- (!\seqgen|cnt[7]~DUPLICATE_q\)))) # (\seqgen|cnt\(6) & (((\seqgen|cnt[7]~DUPLICATE_q\)))) ) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000010000111011000010010011010111000000000110001110111010001101",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \seqgen|ALT_INV_cnt[4]~DUPLICATE_q\,
	datab => \seqgen|ALT_INV_cnt\(6),
	datac => \seqgen|ALT_INV_cnt\(0),
	datad => \seqgen|ALT_INV_cnt[7]~DUPLICATE_q\,
	datae => \seqgen|ALT_INV_cnt\(2),
	dataf => \seqgen|ALT_INV_cnt[5]~DUPLICATE_q\,
	combout => \seqgen|Mux0~2_combout\);

-- Location: FF_X74_Y8_N49
\seqgen|cnt[7]\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk_div|ALT_INV_en_out~0_combout\,
	asdata => \seqgen|Add0~1_sumout\,
	clrn => \reset~input_o\,
	sload => VCC,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \seqgen|cnt\(7));

-- Location: LABCELL_X74_Y8_N12
\seqgen|Mux0~3\ : cyclonev_lcell_comb
-- Equation(s):
-- \seqgen|Mux0~3_combout\ = ( \seqgen|cnt\(0) & ( \seqgen|cnt[5]~DUPLICATE_q\ & ( (!\seqgen|cnt\(7) & (!\seqgen|cnt[4]~DUPLICATE_q\ & ((!\seqgen|cnt\(2)) # (!\seqgen|cnt\(6))))) # (\seqgen|cnt\(7) & (!\seqgen|cnt\(6) $ (((\seqgen|cnt\(2) & 
-- !\seqgen|cnt[4]~DUPLICATE_q\))))) ) ) ) # ( !\seqgen|cnt\(0) & ( \seqgen|cnt[5]~DUPLICATE_q\ & ( (!\seqgen|cnt\(7) & ((!\seqgen|cnt\(6) & (\seqgen|cnt\(2))) # (\seqgen|cnt\(6) & ((\seqgen|cnt[4]~DUPLICATE_q\))))) # (\seqgen|cnt\(7) & ((!\seqgen|cnt\(2) & 
-- ((\seqgen|cnt\(6)))) # (\seqgen|cnt\(2) & (!\seqgen|cnt[4]~DUPLICATE_q\)))) ) ) ) # ( \seqgen|cnt\(0) & ( !\seqgen|cnt[5]~DUPLICATE_q\ & ( (!\seqgen|cnt\(2) & (((!\seqgen|cnt[4]~DUPLICATE_q\ & \seqgen|cnt\(6))))) # (\seqgen|cnt\(2) & (((!\seqgen|cnt\(6))) 
-- # (\seqgen|cnt\(7)))) ) ) ) # ( !\seqgen|cnt\(0) & ( !\seqgen|cnt[5]~DUPLICATE_q\ & ( (!\seqgen|cnt\(7) & (!\seqgen|cnt\(6) $ (((!\seqgen|cnt[4]~DUPLICATE_q\) # (\seqgen|cnt\(2)))))) # (\seqgen|cnt\(7) & ((!\seqgen|cnt\(2) & 
-- ((!\seqgen|cnt[4]~DUPLICATE_q\) # (\seqgen|cnt\(6)))) # (\seqgen|cnt\(2) & (\seqgen|cnt[4]~DUPLICATE_q\)))) ) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0010100111100111010101011011000101010100001111101110001110010000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \seqgen|ALT_INV_cnt\(2),
	datab => \seqgen|ALT_INV_cnt\(7),
	datac => \seqgen|ALT_INV_cnt[4]~DUPLICATE_q\,
	datad => \seqgen|ALT_INV_cnt\(6),
	datae => \seqgen|ALT_INV_cnt\(0),
	dataf => \seqgen|ALT_INV_cnt[5]~DUPLICATE_q\,
	combout => \seqgen|Mux0~3_combout\);

-- Location: LABCELL_X74_Y8_N18
\seqgen|Mux0~4\ : cyclonev_lcell_comb
-- Equation(s):
-- \seqgen|Mux0~4_combout\ = ( \seqgen|cnt\(3) & ( \seqgen|Mux0~3_combout\ & ( (\seqgen|cnt\(1)) # (\seqgen|Mux0~1_combout\) ) ) ) # ( !\seqgen|cnt\(3) & ( \seqgen|Mux0~3_combout\ & ( (!\seqgen|cnt\(1) & (\seqgen|Mux0~0_combout\)) # (\seqgen|cnt\(1) & 
-- ((!\seqgen|Mux0~2_combout\))) ) ) ) # ( \seqgen|cnt\(3) & ( !\seqgen|Mux0~3_combout\ & ( (\seqgen|Mux0~1_combout\ & !\seqgen|cnt\(1)) ) ) ) # ( !\seqgen|cnt\(3) & ( !\seqgen|Mux0~3_combout\ & ( (!\seqgen|cnt\(1) & (\seqgen|Mux0~0_combout\)) # 
-- (\seqgen|cnt\(1) & ((!\seqgen|Mux0~2_combout\))) ) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0011111100001100010001000100010000111111000011000111011101110111",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \seqgen|ALT_INV_Mux0~1_combout\,
	datab => \seqgen|ALT_INV_cnt\(1),
	datac => \seqgen|ALT_INV_Mux0~0_combout\,
	datad => \seqgen|ALT_INV_Mux0~2_combout\,
	datae => \seqgen|ALT_INV_cnt\(3),
	dataf => \seqgen|ALT_INV_Mux0~3_combout\,
	combout => \seqgen|Mux0~4_combout\);

-- Location: LABCELL_X74_Y8_N57
\seqcounter|seq_detect|miniFSM1|Selector0~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \seqcounter|seq_detect|miniFSM1|Selector0~0_combout\ = ( \seqgen|Mux0~4_combout\ ) # ( !\seqgen|Mux0~4_combout\ & ( (\seqcounter|seq_detect|miniFSM1|currentState.A0~q\ & !\seqcounter|seq_detect|miniFSM1|currentState.A2~q\) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000111100000000000011110000000011111111111111111111111111111111",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datac => \seqcounter|seq_detect|miniFSM1|ALT_INV_currentState.A0~q\,
	datad => \seqcounter|seq_detect|miniFSM1|ALT_INV_currentState.A2~q\,
	dataf => \seqgen|ALT_INV_Mux0~4_combout\,
	combout => \seqcounter|seq_detect|miniFSM1|Selector0~0_combout\);

-- Location: FF_X74_Y8_N32
\seqcounter|seq_detect|miniFSM1|currentState.A0\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk_div|en_out~0_combout\,
	asdata => \seqcounter|seq_detect|miniFSM1|Selector0~0_combout\,
	clrn => \reset~input_o\,
	sload => VCC,
	ena => \enable~input_o\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \seqcounter|seq_detect|miniFSM1|currentState.A0~q\);

-- Location: LABCELL_X74_Y8_N30
\seqcounter|seq_detect|miniFSM1|currentState~9\ : cyclonev_lcell_comb
-- Equation(s):
-- \seqcounter|seq_detect|miniFSM1|currentState~9_combout\ = ( \seqcounter|seq_detect|miniFSM1|currentState.A0~q\ & ( (!\enable~input_o\ & (\seqcounter|seq_detect|miniFSM1|currentState.A2~q\)) # (\enable~input_o\ & 
-- (!\seqcounter|seq_detect|miniFSM1|currentState.A2~q\ & !\seqgen|Mux0~4_combout\)) ) ) # ( !\seqcounter|seq_detect|miniFSM1|currentState.A0~q\ & ( (!\enable~input_o\ & \seqcounter|seq_detect|miniFSM1|currentState.A2~q\) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0010001000100010011000100110001000100010001000100110001001100010",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \ALT_INV_enable~input_o\,
	datab => \seqcounter|seq_detect|miniFSM1|ALT_INV_currentState.A2~q\,
	datac => \seqgen|ALT_INV_Mux0~4_combout\,
	datae => \seqcounter|seq_detect|miniFSM1|ALT_INV_currentState.A0~q\,
	combout => \seqcounter|seq_detect|miniFSM1|currentState~9_combout\);

-- Location: FF_X74_Y8_N59
\seqcounter|seq_detect|miniFSM1|currentState.A2\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk_div|en_out~0_combout\,
	asdata => \seqcounter|seq_detect|miniFSM1|currentState~9_combout\,
	clrn => \reset~input_o\,
	sload => VCC,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \seqcounter|seq_detect|miniFSM1|currentState.A2~q\);

-- Location: LABCELL_X74_Y8_N6
\seqcounter|seq_detect|miniFSM1|Selector3~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \seqcounter|seq_detect|miniFSM1|Selector3~0_combout\ = ( \seqgen|Mux0~4_combout\ & ( \seqcounter|seq_detect|miniFSM1|currentState.A2~q\ ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000000000000000000000110011001100110011001100110011",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datab => \seqcounter|seq_detect|miniFSM1|ALT_INV_currentState.A2~q\,
	dataf => \seqgen|ALT_INV_Mux0~4_combout\,
	combout => \seqcounter|seq_detect|miniFSM1|Selector3~0_combout\);

-- Location: FF_X74_Y8_N7
\seqcounter|seq_detect|miniFSM1|currentState.A3\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk_div|en_out~0_combout\,
	d => \seqcounter|seq_detect|miniFSM1|Selector3~0_combout\,
	clrn => \reset~input_o\,
	ena => \enable~input_o\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \seqcounter|seq_detect|miniFSM1|currentState.A3~q\);

-- Location: LABCELL_X74_Y8_N9
\seqcounter|seq_detect|miniFSM1|Selector4~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \seqcounter|seq_detect|miniFSM1|Selector4~0_combout\ = ( \seqgen|Mux0~4_combout\ & ( \seqcounter|seq_detect|miniFSM1|currentState.A3~q\ ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000000000000000000000001111000011110000111100001111",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datac => \seqcounter|seq_detect|miniFSM1|ALT_INV_currentState.A3~q\,
	dataf => \seqgen|ALT_INV_Mux0~4_combout\,
	combout => \seqcounter|seq_detect|miniFSM1|Selector4~0_combout\);

-- Location: FF_X74_Y8_N11
\seqcounter|seq_detect|miniFSM1|currentState.A4\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk_div|en_out~0_combout\,
	d => \seqcounter|seq_detect|miniFSM1|Selector4~0_combout\,
	clrn => \reset~input_o\,
	ena => \enable~input_o\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \seqcounter|seq_detect|miniFSM1|currentState.A4~q\);

-- Location: LABCELL_X75_Y8_N6
\seqcounter|counter1|j0|sigQ~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \seqcounter|counter1|j0|sigQ~0_combout\ = ( !\seqcounter|counter1|j0|sigQ~q\ )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "1111111111111111111111111111111100000000000000000000000000000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataf => \seqcounter|counter1|j0|ALT_INV_sigQ~q\,
	combout => \seqcounter|counter1|j0|sigQ~0_combout\);

-- Location: LABCELL_X75_Y8_N24
\seqcounter|counter1|j0|sigQ~feeder\ : cyclonev_lcell_comb
-- Equation(s):
-- \seqcounter|counter1|j0|sigQ~feeder_combout\ = ( \seqcounter|counter1|j0|sigQ~0_combout\ )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000000000000000000011111111111111111111111111111111",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataf => \seqcounter|counter1|j0|ALT_INV_sigQ~0_combout\,
	combout => \seqcounter|counter1|j0|sigQ~feeder_combout\);

-- Location: FF_X75_Y8_N26
\seqcounter|counter1|j0|sigQ\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \seqcounter|seq_detect|miniFSM1|currentState.A4~q\,
	d => \seqcounter|counter1|j0|sigQ~feeder_combout\,
	clrn => \reset~input_o\,
	ena => \enable~input_o\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \seqcounter|counter1|j0|sigQ~q\);

-- Location: LABCELL_X75_Y8_N45
\seqcounter|counter1|j1|sigQ~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \seqcounter|counter1|j1|sigQ~0_combout\ = ( \seqcounter|counter1|j1|sigQ~q\ & ( \seqcounter|counter1|j0|sigQ~q\ & ( !\enable~input_o\ ) ) ) # ( !\seqcounter|counter1|j1|sigQ~q\ & ( \seqcounter|counter1|j0|sigQ~q\ & ( \enable~input_o\ ) ) ) # ( 
-- \seqcounter|counter1|j1|sigQ~q\ & ( !\seqcounter|counter1|j0|sigQ~q\ ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000111111111111111101010101010101011010101010101010",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \ALT_INV_enable~input_o\,
	datae => \seqcounter|counter1|j1|ALT_INV_sigQ~q\,
	dataf => \seqcounter|counter1|j0|ALT_INV_sigQ~q\,
	combout => \seqcounter|counter1|j1|sigQ~0_combout\);

-- Location: FF_X75_Y8_N59
\seqcounter|counter1|j1|sigQ\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \seqcounter|seq_detect|miniFSM1|currentState.A4~q\,
	asdata => \seqcounter|counter1|j1|sigQ~0_combout\,
	clrn => \reset~input_o\,
	sload => VCC,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \seqcounter|counter1|j1|sigQ~q\);

-- Location: LABCELL_X75_Y8_N0
\seqcounter|counter1|j2|sigQ~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \seqcounter|counter1|j2|sigQ~0_combout\ = ( \seqcounter|counter1|j2|sigQ~q\ & ( (!\enable~input_o\) # ((!\seqcounter|counter1|j0|sigQ~q\) # (!\seqcounter|counter1|j1|sigQ~q\)) ) ) # ( !\seqcounter|counter1|j2|sigQ~q\ & ( (\enable~input_o\ & 
-- (\seqcounter|counter1|j0|sigQ~q\ & \seqcounter|counter1|j1|sigQ~q\)) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000101000000000000010111111111111110101111111111111010",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \ALT_INV_enable~input_o\,
	datac => \seqcounter|counter1|j0|ALT_INV_sigQ~q\,
	datad => \seqcounter|counter1|j1|ALT_INV_sigQ~q\,
	dataf => \seqcounter|counter1|j2|ALT_INV_sigQ~q\,
	combout => \seqcounter|counter1|j2|sigQ~0_combout\);

-- Location: FF_X75_Y8_N20
\seqcounter|counter1|j2|sigQ\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \seqcounter|seq_detect|miniFSM1|currentState.A4~q\,
	asdata => \seqcounter|counter1|j2|sigQ~0_combout\,
	clrn => \reset~input_o\,
	sload => VCC,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \seqcounter|counter1|j2|sigQ~q\);

-- Location: LABCELL_X75_Y8_N3
\hexdis0|Mux6~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \hexdis0|Mux6~0_combout\ = ( \seqcounter|counter1|j0|sigQ~q\ & ( (!\seqcounter|counter1|j1|sigQ~q\ & !\seqcounter|counter1|j2|sigQ~q\) ) ) # ( !\seqcounter|counter1|j0|sigQ~q\ & ( (!\seqcounter|counter1|j1|sigQ~q\ & \seqcounter|counter1|j2|sigQ~q\) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000110000001100000011000000110011000000110000001100000011000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datab => \seqcounter|counter1|j1|ALT_INV_sigQ~q\,
	datac => \seqcounter|counter1|j2|ALT_INV_sigQ~q\,
	dataf => \seqcounter|counter1|j0|ALT_INV_sigQ~q\,
	combout => \hexdis0|Mux6~0_combout\);

-- Location: LABCELL_X75_Y8_N21
\hexdis0|Mux5~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \hexdis0|Mux5~0_combout\ = ( \seqcounter|counter1|j0|sigQ~q\ & ( (!\seqcounter|counter1|j1|sigQ~q\ & \seqcounter|counter1|j2|sigQ~q\) ) ) # ( !\seqcounter|counter1|j0|sigQ~q\ & ( (\seqcounter|counter1|j1|sigQ~q\ & \seqcounter|counter1|j2|sigQ~q\) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000001100000011000000110000001100001100000011000000110000001100",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datab => \seqcounter|counter1|j1|ALT_INV_sigQ~q\,
	datac => \seqcounter|counter1|j2|ALT_INV_sigQ~q\,
	dataf => \seqcounter|counter1|j0|ALT_INV_sigQ~q\,
	combout => \hexdis0|Mux5~0_combout\);

-- Location: LABCELL_X75_Y8_N48
\hexdis0|Mux4~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \hexdis0|Mux4~0_combout\ = ( !\seqcounter|counter1|j2|sigQ~q\ & ( !\seqcounter|counter1|j0|sigQ~q\ & ( \seqcounter|counter1|j1|sigQ~q\ ) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0011001100110011000000000000000000000000000000000000000000000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datab => \seqcounter|counter1|j1|ALT_INV_sigQ~q\,
	datae => \seqcounter|counter1|j2|ALT_INV_sigQ~q\,
	dataf => \seqcounter|counter1|j0|ALT_INV_sigQ~q\,
	combout => \hexdis0|Mux4~0_combout\);

-- Location: LABCELL_X75_Y8_N9
\hexdis0|Mux3~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \hexdis0|Mux3~0_combout\ = (!\seqcounter|counter1|j0|sigQ~q\ & (!\seqcounter|counter1|j1|sigQ~q\ & \seqcounter|counter1|j2|sigQ~q\)) # (\seqcounter|counter1|j0|sigQ~q\ & (!\seqcounter|counter1|j1|sigQ~q\ $ (\seqcounter|counter1|j2|sigQ~q\)))

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0011000011000011001100001100001100110000110000110011000011000011",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datab => \seqcounter|counter1|j0|ALT_INV_sigQ~q\,
	datac => \seqcounter|counter1|j1|ALT_INV_sigQ~q\,
	datad => \seqcounter|counter1|j2|ALT_INV_sigQ~q\,
	combout => \hexdis0|Mux3~0_combout\);

-- Location: LABCELL_X75_Y8_N36
\hexdis0|Mux2~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \hexdis0|Mux2~0_combout\ = ( \seqcounter|counter1|j2|sigQ~q\ & ( \seqcounter|counter1|j0|sigQ~q\ ) ) # ( !\seqcounter|counter1|j2|sigQ~q\ & ( \seqcounter|counter1|j0|sigQ~q\ ) ) # ( \seqcounter|counter1|j2|sigQ~q\ & ( !\seqcounter|counter1|j0|sigQ~q\ & ( 
-- !\seqcounter|counter1|j1|sigQ~q\ ) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000110011001100110011111111111111111111111111111111",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datab => \seqcounter|counter1|j1|ALT_INV_sigQ~q\,
	datae => \seqcounter|counter1|j2|ALT_INV_sigQ~q\,
	dataf => \seqcounter|counter1|j0|ALT_INV_sigQ~q\,
	combout => \hexdis0|Mux2~0_combout\);

-- Location: LABCELL_X75_Y8_N33
\hexdis0|Mux1~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \hexdis0|Mux1~0_combout\ = ( \seqcounter|counter1|j2|sigQ~q\ & ( \seqcounter|counter1|j0|sigQ~q\ & ( \seqcounter|counter1|j1|sigQ~q\ ) ) ) # ( !\seqcounter|counter1|j2|sigQ~q\ & ( \seqcounter|counter1|j0|sigQ~q\ ) ) # ( !\seqcounter|counter1|j2|sigQ~q\ & 
-- ( !\seqcounter|counter1|j0|sigQ~q\ & ( \seqcounter|counter1|j1|sigQ~q\ ) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000111100001111000000000000000011111111111111110000111100001111",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datac => \seqcounter|counter1|j1|ALT_INV_sigQ~q\,
	datae => \seqcounter|counter1|j2|ALT_INV_sigQ~q\,
	dataf => \seqcounter|counter1|j0|ALT_INV_sigQ~q\,
	combout => \hexdis0|Mux1~0_combout\);

-- Location: LABCELL_X75_Y8_N18
\hexdis0|Mux0~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \hexdis0|Mux0~0_combout\ = ( \seqcounter|counter1|j0|sigQ~q\ & ( !\seqcounter|counter1|j1|sigQ~q\ $ (\seqcounter|counter1|j2|sigQ~q\) ) ) # ( !\seqcounter|counter1|j0|sigQ~q\ & ( (!\seqcounter|counter1|j1|sigQ~q\ & !\seqcounter|counter1|j2|sigQ~q\) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "1100110000000000110011000000000011001100001100111100110000110011",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datab => \seqcounter|counter1|j1|ALT_INV_sigQ~q\,
	datad => \seqcounter|counter1|j2|ALT_INV_sigQ~q\,
	dataf => \seqcounter|counter1|j0|ALT_INV_sigQ~q\,
	combout => \hexdis0|Mux0~0_combout\);

-- Location: LABCELL_X74_Y8_N45
\seqcounter|seq_detect|miniFSM2|Selector2~2\ : cyclonev_lcell_comb
-- Equation(s):
-- \seqcounter|seq_detect|miniFSM2|Selector2~2_combout\ = ( \seqcounter|seq_detect|miniFSM2|currentState.A2~q\ ) # ( !\seqcounter|seq_detect|miniFSM2|currentState.A2~q\ & ( !\seqgen|Mux0~4_combout\ ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "1111111100000000111111110000000011111111111111111111111111111111",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datad => \seqgen|ALT_INV_Mux0~4_combout\,
	dataf => \seqcounter|seq_detect|miniFSM2|ALT_INV_currentState.A2~q\,
	combout => \seqcounter|seq_detect|miniFSM2|Selector2~2_combout\);

-- Location: FF_X74_Y8_N46
\seqcounter|seq_detect|miniFSM2|currentState.A0\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk_div|en_out~0_combout\,
	d => \seqcounter|seq_detect|miniFSM2|Selector2~2_combout\,
	clrn => \reset~input_o\,
	ena => \enable~input_o\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \seqcounter|seq_detect|miniFSM2|currentState.A0~q\);

-- Location: LABCELL_X74_Y8_N42
\seqcounter|seq_detect|miniFSM2|Selector1~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \seqcounter|seq_detect|miniFSM2|Selector1~0_combout\ = ( !\seqgen|Mux0~4_combout\ & ( !\seqcounter|seq_detect|miniFSM2|currentState.A0~q\ ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "1111111100000000111111110000000000000000000000000000000000000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datad => \seqcounter|seq_detect|miniFSM2|ALT_INV_currentState.A0~q\,
	dataf => \seqgen|ALT_INV_Mux0~4_combout\,
	combout => \seqcounter|seq_detect|miniFSM2|Selector1~0_combout\);

-- Location: FF_X74_Y8_N43
\seqcounter|seq_detect|miniFSM2|currentState.A1\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk_div|en_out~0_combout\,
	d => \seqcounter|seq_detect|miniFSM2|Selector1~0_combout\,
	clrn => \reset~input_o\,
	ena => \enable~input_o\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \seqcounter|seq_detect|miniFSM2|currentState.A1~q\);

-- Location: LABCELL_X74_Y8_N54
\seqcounter|seq_detect|miniFSM2|Selector2~1\ : cyclonev_lcell_comb
-- Equation(s):
-- \seqcounter|seq_detect|miniFSM2|Selector2~1_combout\ = ( !\seqgen|Mux0~4_combout\ & ( ((\seqcounter|seq_detect|miniFSM2|currentState.A2~q\) # (\seqcounter|seq_detect|miniFSM2|currentState.A1~q\)) # (\seqcounter|seq_detect|miniFSM2|currentState.A4~q\) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0101111111111111010111111111111100000000000000000000000000000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \seqcounter|seq_detect|miniFSM2|ALT_INV_currentState.A4~q\,
	datac => \seqcounter|seq_detect|miniFSM2|ALT_INV_currentState.A1~q\,
	datad => \seqcounter|seq_detect|miniFSM2|ALT_INV_currentState.A2~q\,
	dataf => \seqgen|ALT_INV_Mux0~4_combout\,
	combout => \seqcounter|seq_detect|miniFSM2|Selector2~1_combout\);

-- Location: FF_X74_Y8_N35
\seqcounter|seq_detect|miniFSM2|currentState.A2\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk_div|en_out~0_combout\,
	asdata => \seqcounter|seq_detect|miniFSM2|Selector2~1_combout\,
	clrn => \reset~input_o\,
	sload => VCC,
	ena => \enable~input_o\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \seqcounter|seq_detect|miniFSM2|currentState.A2~q\);

-- Location: LABCELL_X74_Y8_N27
\seqcounter|seq_detect|miniFSM2|Selector2~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \seqcounter|seq_detect|miniFSM2|Selector2~0_combout\ = ( \seqcounter|seq_detect|miniFSM2|currentState.A2~q\ & ( \seqgen|Mux0~4_combout\ ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000000000000000000000000000111111110000000011111111",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datad => \seqgen|ALT_INV_Mux0~4_combout\,
	dataf => \seqcounter|seq_detect|miniFSM2|ALT_INV_currentState.A2~q\,
	combout => \seqcounter|seq_detect|miniFSM2|Selector2~0_combout\);

-- Location: FF_X74_Y8_N28
\seqcounter|seq_detect|miniFSM2|currentState.A3\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk_div|en_out~0_combout\,
	d => \seqcounter|seq_detect|miniFSM2|Selector2~0_combout\,
	clrn => \reset~input_o\,
	ena => \enable~input_o\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \seqcounter|seq_detect|miniFSM2|currentState.A3~q\);

-- Location: LABCELL_X74_Y8_N24
\seqcounter|seq_detect|miniFSM2|Selector4~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \seqcounter|seq_detect|miniFSM2|Selector4~0_combout\ = ( \seqcounter|seq_detect|miniFSM2|currentState.A3~q\ & ( !\seqgen|Mux0~4_combout\ ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000000000000000000011110000111100001111000011110000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datac => \seqgen|ALT_INV_Mux0~4_combout\,
	dataf => \seqcounter|seq_detect|miniFSM2|ALT_INV_currentState.A3~q\,
	combout => \seqcounter|seq_detect|miniFSM2|Selector4~0_combout\);

-- Location: FF_X74_Y8_N26
\seqcounter|seq_detect|miniFSM2|currentState.A4\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk_div|en_out~0_combout\,
	d => \seqcounter|seq_detect|miniFSM2|Selector4~0_combout\,
	clrn => \reset~input_o\,
	ena => \enable~input_o\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \seqcounter|seq_detect|miniFSM2|currentState.A4~q\);

-- Location: LABCELL_X73_Y8_N45
\seqcounter|counter2|j0|sigQ~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \seqcounter|counter2|j0|sigQ~0_combout\ = ( !\seqcounter|counter2|j0|sigQ~q\ )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "1111111111111111111111111111111100000000000000000000000000000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataf => \seqcounter|counter2|j0|ALT_INV_sigQ~q\,
	combout => \seqcounter|counter2|j0|sigQ~0_combout\);

-- Location: FF_X73_Y8_N50
\seqcounter|counter2|j0|sigQ\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \seqcounter|seq_detect|miniFSM2|currentState.A4~q\,
	asdata => \seqcounter|counter2|j0|sigQ~0_combout\,
	clrn => \reset~input_o\,
	sload => VCC,
	ena => \enable~input_o\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \seqcounter|counter2|j0|sigQ~q\);

-- Location: LABCELL_X73_Y8_N42
\seqcounter|counter2|j1|sigQ~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \seqcounter|counter2|j1|sigQ~0_combout\ = ( \seqcounter|counter2|j1|sigQ~q\ & ( (!\seqcounter|counter2|j0|sigQ~q\) # (!\enable~input_o\) ) ) # ( !\seqcounter|counter2|j1|sigQ~q\ & ( (\seqcounter|counter2|j0|sigQ~q\ & \enable~input_o\) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000001111000000000000111111111111111100001111111111110000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datac => \seqcounter|counter2|j0|ALT_INV_sigQ~q\,
	datad => \ALT_INV_enable~input_o\,
	dataf => \seqcounter|counter2|j1|ALT_INV_sigQ~q\,
	combout => \seqcounter|counter2|j1|sigQ~0_combout\);

-- Location: FF_X73_Y8_N35
\seqcounter|counter2|j1|sigQ\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \seqcounter|seq_detect|miniFSM2|currentState.A4~q\,
	asdata => \seqcounter|counter2|j1|sigQ~0_combout\,
	clrn => \reset~input_o\,
	sload => VCC,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \seqcounter|counter2|j1|sigQ~q\);

-- Location: LABCELL_X73_Y8_N27
\seqcounter|counter2|j2|sigQ~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \seqcounter|counter2|j2|sigQ~0_combout\ = ( \seqcounter|counter2|j2|sigQ~q\ & ( \seqcounter|counter2|j0|sigQ~q\ & ( (!\seqcounter|counter2|j1|sigQ~q\) # (!\enable~input_o\) ) ) ) # ( !\seqcounter|counter2|j2|sigQ~q\ & ( \seqcounter|counter2|j0|sigQ~q\ & ( 
-- (\seqcounter|counter2|j1|sigQ~q\ & \enable~input_o\) ) ) ) # ( \seqcounter|counter2|j2|sigQ~q\ & ( !\seqcounter|counter2|j0|sigQ~q\ ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000111111111111111100000101000001011111101011111010",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \seqcounter|counter2|j1|ALT_INV_sigQ~q\,
	datac => \ALT_INV_enable~input_o\,
	datae => \seqcounter|counter2|j2|ALT_INV_sigQ~q\,
	dataf => \seqcounter|counter2|j0|ALT_INV_sigQ~q\,
	combout => \seqcounter|counter2|j2|sigQ~0_combout\);

-- Location: FF_X73_Y8_N56
\seqcounter|counter2|j2|sigQ\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \seqcounter|seq_detect|miniFSM2|currentState.A4~q\,
	asdata => \seqcounter|counter2|j2|sigQ~0_combout\,
	clrn => \reset~input_o\,
	sload => VCC,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \seqcounter|counter2|j2|sigQ~q\);

-- Location: LABCELL_X71_Y10_N36
\hexdis5|Mux6~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \hexdis5|Mux6~0_combout\ = ( \seqcounter|counter2|j2|sigQ~q\ & ( (!\seqcounter|counter2|j0|sigQ~q\ & !\seqcounter|counter2|j1|sigQ~q\) ) ) # ( !\seqcounter|counter2|j2|sigQ~q\ & ( (\seqcounter|counter2|j0|sigQ~q\ & !\seqcounter|counter2|j1|sigQ~q\) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0101000001010000010100000101000010100000101000001010000010100000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \seqcounter|counter2|j0|ALT_INV_sigQ~q\,
	datac => \seqcounter|counter2|j1|ALT_INV_sigQ~q\,
	dataf => \seqcounter|counter2|j2|ALT_INV_sigQ~q\,
	combout => \hexdis5|Mux6~0_combout\);

-- Location: MLABCELL_X78_Y10_N15
\hexdis5|Mux5~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \hexdis5|Mux5~0_combout\ = ( \seqcounter|counter2|j2|sigQ~q\ & ( !\seqcounter|counter2|j1|sigQ~q\ $ (!\seqcounter|counter2|j0|sigQ~q\) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000000000000000000001011010010110100101101001011010",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \seqcounter|counter2|j1|ALT_INV_sigQ~q\,
	datac => \seqcounter|counter2|j0|ALT_INV_sigQ~q\,
	dataf => \seqcounter|counter2|j2|ALT_INV_sigQ~q\,
	combout => \hexdis5|Mux5~0_combout\);

-- Location: MLABCELL_X78_Y10_N30
\hexdis5|Mux4~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \hexdis5|Mux4~0_combout\ = (\seqcounter|counter2|j1|sigQ~q\ & (!\seqcounter|counter2|j0|sigQ~q\ & !\seqcounter|counter2|j2|sigQ~q\))

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0100000001000000010000000100000001000000010000000100000001000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \seqcounter|counter2|j1|ALT_INV_sigQ~q\,
	datab => \seqcounter|counter2|j0|ALT_INV_sigQ~q\,
	datac => \seqcounter|counter2|j2|ALT_INV_sigQ~q\,
	combout => \hexdis5|Mux4~0_combout\);

-- Location: LABCELL_X71_Y10_N33
\hexdis5|Mux3~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \hexdis5|Mux3~0_combout\ = ( \seqcounter|counter2|j2|sigQ~q\ & ( !\seqcounter|counter2|j1|sigQ~q\ $ (\seqcounter|counter2|j0|sigQ~q\) ) ) # ( !\seqcounter|counter2|j2|sigQ~q\ & ( (!\seqcounter|counter2|j1|sigQ~q\ & \seqcounter|counter2|j0|sigQ~q\) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000010101010000000001010101010101010010101011010101001010101",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \seqcounter|counter2|j1|ALT_INV_sigQ~q\,
	datad => \seqcounter|counter2|j0|ALT_INV_sigQ~q\,
	dataf => \seqcounter|counter2|j2|ALT_INV_sigQ~q\,
	combout => \hexdis5|Mux3~0_combout\);

-- Location: MLABCELL_X78_Y9_N36
\hexdis5|Mux2~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \hexdis5|Mux2~0_combout\ = ( \seqcounter|counter2|j2|sigQ~q\ & ( (!\seqcounter|counter2|j1|sigQ~q\) # (\seqcounter|counter2|j0|sigQ~q\) ) ) # ( !\seqcounter|counter2|j2|sigQ~q\ & ( \seqcounter|counter2|j0|sigQ~q\ ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0101010101010101010101010101010111110101111101011111010111110101",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \seqcounter|counter2|j0|ALT_INV_sigQ~q\,
	datac => \seqcounter|counter2|j1|ALT_INV_sigQ~q\,
	dataf => \seqcounter|counter2|j2|ALT_INV_sigQ~q\,
	combout => \hexdis5|Mux2~0_combout\);

-- Location: LABCELL_X71_Y10_N12
\hexdis5|Mux1~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \hexdis5|Mux1~0_combout\ = ( \seqcounter|counter2|j2|sigQ~q\ & ( (\seqcounter|counter2|j0|sigQ~q\ & \seqcounter|counter2|j1|sigQ~q\) ) ) # ( !\seqcounter|counter2|j2|sigQ~q\ & ( (\seqcounter|counter2|j1|sigQ~q\) # (\seqcounter|counter2|j0|sigQ~q\) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0101111101011111010111110101111100000101000001010000010100000101",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \seqcounter|counter2|j0|ALT_INV_sigQ~q\,
	datac => \seqcounter|counter2|j1|ALT_INV_sigQ~q\,
	dataf => \seqcounter|counter2|j2|ALT_INV_sigQ~q\,
	combout => \hexdis5|Mux1~0_combout\);

-- Location: LABCELL_X74_Y9_N36
\hexdis5|Mux0~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \hexdis5|Mux0~0_combout\ = ( \seqcounter|counter2|j2|sigQ~q\ & ( (\seqcounter|counter2|j1|sigQ~q\ & \seqcounter|counter2|j0|sigQ~q\) ) ) # ( !\seqcounter|counter2|j2|sigQ~q\ & ( !\seqcounter|counter2|j1|sigQ~q\ ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "1010101010101010101010101010101000010001000100010001000100010001",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \seqcounter|counter2|j1|ALT_INV_sigQ~q\,
	datab => \seqcounter|counter2|j0|ALT_INV_sigQ~q\,
	dataf => \seqcounter|counter2|j2|ALT_INV_sigQ~q\,
	combout => \hexdis5|Mux0~0_combout\);

-- Location: LABCELL_X29_Y33_N3
\~QUARTUS_CREATED_GND~I\ : cyclonev_lcell_comb
-- Equation(s):

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000000000000000000000000000000000000000000000000000",
	shared_arith => "off")
-- pragma translate_on
;
END structure;


