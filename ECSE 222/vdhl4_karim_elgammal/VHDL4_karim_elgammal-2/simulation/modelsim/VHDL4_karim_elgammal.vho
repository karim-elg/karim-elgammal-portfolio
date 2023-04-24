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

-- DATE "10/28/2022 20:07:53"

-- 
-- Device: Altera 5CSEMA5F31C6 Package FBGA896
-- 

-- 
-- This VHDL file should be used for ModelSim-Altera (VHDL) only
-- 

LIBRARY ALTERA_LNSIM;
LIBRARY CYCLONEV;
LIBRARY IEEE;
USE ALTERA_LNSIM.ALTERA_LNSIM_COMPONENTS.ALL;
USE CYCLONEV.CYCLONEV_COMPONENTS.ALL;
USE IEEE.STD_LOGIC_1164.ALL;

ENTITY 	bcd_adder_structural IS
    PORT (
	A : IN std_logic_vector(3 DOWNTO 0);
	B : IN std_logic_vector(3 DOWNTO 0);
	S : BUFFER std_logic_vector(3 DOWNTO 0);
	C : BUFFER std_logic
	);
END bcd_adder_structural;

-- Design Ports Information
-- S[0]	=>  Location: PIN_AH2,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- S[1]	=>  Location: PIN_AG7,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- S[2]	=>  Location: PIN_AF8,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- S[3]	=>  Location: PIN_AG1,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- C	=>  Location: PIN_AG8,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- A[0]	=>  Location: PIN_AE11,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- B[0]	=>  Location: PIN_AB12,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- A[1]	=>  Location: PIN_AC9,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- B[1]	=>  Location: PIN_AC12,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- A[3]	=>  Location: PIN_AE12,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- B[3]	=>  Location: PIN_AF10,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- A[2]	=>  Location: PIN_AD10,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- B[2]	=>  Location: PIN_AF9,	 I/O Standard: 2.5 V,	 Current Strength: Default


ARCHITECTURE structure OF bcd_adder_structural IS
SIGNAL gnd : std_logic := '0';
SIGNAL vcc : std_logic := '1';
SIGNAL unknown : std_logic := 'X';
SIGNAL devoe : std_logic := '1';
SIGNAL devclrn : std_logic := '1';
SIGNAL devpor : std_logic := '1';
SIGNAL ww_devoe : std_logic;
SIGNAL ww_devclrn : std_logic;
SIGNAL ww_devpor : std_logic;
SIGNAL ww_A : std_logic_vector(3 DOWNTO 0);
SIGNAL ww_B : std_logic_vector(3 DOWNTO 0);
SIGNAL ww_S : std_logic_vector(3 DOWNTO 0);
SIGNAL ww_C : std_logic;
SIGNAL \~QUARTUS_CREATED_GND~I_combout\ : std_logic;
SIGNAL \B[0]~input_o\ : std_logic;
SIGNAL \A[0]~input_o\ : std_logic;
SIGNAL \rca1|adder_0|s~combout\ : std_logic;
SIGNAL \B[3]~input_o\ : std_logic;
SIGNAL \B[1]~input_o\ : std_logic;
SIGNAL \A[1]~input_o\ : std_logic;
SIGNAL \rca1|adder_1|ha_1|s~combout\ : std_logic;
SIGNAL \A[3]~input_o\ : std_logic;
SIGNAL \B[2]~input_o\ : std_logic;
SIGNAL \A[2]~input_o\ : std_logic;
SIGNAL \c1~0_combout\ : std_logic;
SIGNAL \rca1|adder_2|c_out~combout\ : std_logic;
SIGNAL \rca2|adder_1|ha_0|s~combout\ : std_logic;
SIGNAL \rca1|adder_1|c_out~combout\ : std_logic;
SIGNAL \rca2|adder_2|ha_1|s~combout\ : std_logic;
SIGNAL \rca2|adder_3|ha_1|s~0_combout\ : std_logic;
SIGNAL \c1~1_combout\ : std_logic;
SIGNAL \ALT_INV_B[2]~input_o\ : std_logic;
SIGNAL \ALT_INV_A[2]~input_o\ : std_logic;
SIGNAL \ALT_INV_B[3]~input_o\ : std_logic;
SIGNAL \ALT_INV_A[3]~input_o\ : std_logic;
SIGNAL \ALT_INV_B[1]~input_o\ : std_logic;
SIGNAL \ALT_INV_A[1]~input_o\ : std_logic;
SIGNAL \ALT_INV_B[0]~input_o\ : std_logic;
SIGNAL \ALT_INV_A[0]~input_o\ : std_logic;
SIGNAL \rca1|adder_1|ALT_INV_c_out~combout\ : std_logic;
SIGNAL \ALT_INV_c1~0_combout\ : std_logic;
SIGNAL \rca1|adder_2|ALT_INV_c_out~combout\ : std_logic;
SIGNAL \rca1|adder_1|ha_1|ALT_INV_s~combout\ : std_logic;

BEGIN

ww_A <= A;
ww_B <= B;
S <= ww_S;
C <= ww_C;
ww_devoe <= devoe;
ww_devclrn <= devclrn;
ww_devpor <= devpor;
\ALT_INV_B[2]~input_o\ <= NOT \B[2]~input_o\;
\ALT_INV_A[2]~input_o\ <= NOT \A[2]~input_o\;
\ALT_INV_B[3]~input_o\ <= NOT \B[3]~input_o\;
\ALT_INV_A[3]~input_o\ <= NOT \A[3]~input_o\;
\ALT_INV_B[1]~input_o\ <= NOT \B[1]~input_o\;
\ALT_INV_A[1]~input_o\ <= NOT \A[1]~input_o\;
\ALT_INV_B[0]~input_o\ <= NOT \B[0]~input_o\;
\ALT_INV_A[0]~input_o\ <= NOT \A[0]~input_o\;
\rca1|adder_1|ALT_INV_c_out~combout\ <= NOT \rca1|adder_1|c_out~combout\;
\ALT_INV_c1~0_combout\ <= NOT \c1~0_combout\;
\rca1|adder_2|ALT_INV_c_out~combout\ <= NOT \rca1|adder_2|c_out~combout\;
\rca1|adder_1|ha_1|ALT_INV_s~combout\ <= NOT \rca1|adder_1|ha_1|s~combout\;

-- Location: IOOBUF_X10_Y0_N59
\S[0]~output\ : cyclonev_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false",
	shift_series_termination_control => "false")
-- pragma translate_on
PORT MAP (
	i => \rca1|adder_0|s~combout\,
	devoe => ww_devoe,
	o => ww_S(0));

-- Location: IOOBUF_X10_Y0_N93
\S[1]~output\ : cyclonev_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false",
	shift_series_termination_control => "false")
-- pragma translate_on
PORT MAP (
	i => \rca2|adder_1|ha_0|s~combout\,
	devoe => ww_devoe,
	o => ww_S(1));

-- Location: IOOBUF_X10_Y0_N76
\S[2]~output\ : cyclonev_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false",
	shift_series_termination_control => "false")
-- pragma translate_on
PORT MAP (
	i => \rca2|adder_2|ha_1|s~combout\,
	devoe => ww_devoe,
	o => ww_S(2));

-- Location: IOOBUF_X10_Y0_N42
\S[3]~output\ : cyclonev_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false",
	shift_series_termination_control => "false")
-- pragma translate_on
PORT MAP (
	i => \rca2|adder_3|ha_1|s~0_combout\,
	devoe => ww_devoe,
	o => ww_S(3));

-- Location: IOOBUF_X8_Y0_N53
\C~output\ : cyclonev_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false",
	shift_series_termination_control => "false")
-- pragma translate_on
PORT MAP (
	i => \c1~1_combout\,
	devoe => ww_devoe,
	o => ww_C);

-- Location: IOIBUF_X12_Y0_N18
\B[0]~input\ : cyclonev_io_ibuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	simulate_z_as => "z")
-- pragma translate_on
PORT MAP (
	i => ww_B(0),
	o => \B[0]~input_o\);

-- Location: IOIBUF_X4_Y0_N35
\A[0]~input\ : cyclonev_io_ibuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	simulate_z_as => "z")
-- pragma translate_on
PORT MAP (
	i => ww_A(0),
	o => \A[0]~input_o\);

-- Location: LABCELL_X11_Y1_N42
\rca1|adder_0|s\ : cyclonev_lcell_comb
-- Equation(s):
-- \rca1|adder_0|s~combout\ = ( !\B[0]~input_o\ & ( \A[0]~input_o\ ) ) # ( \B[0]~input_o\ & ( !\A[0]~input_o\ ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000111111111111111111111111111111110000000000000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datae => \ALT_INV_B[0]~input_o\,
	dataf => \ALT_INV_A[0]~input_o\,
	combout => \rca1|adder_0|s~combout\);

-- Location: IOIBUF_X4_Y0_N52
\B[3]~input\ : cyclonev_io_ibuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	simulate_z_as => "z")
-- pragma translate_on
PORT MAP (
	i => ww_B(3),
	o => \B[3]~input_o\);

-- Location: IOIBUF_X16_Y0_N1
\B[1]~input\ : cyclonev_io_ibuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	simulate_z_as => "z")
-- pragma translate_on
PORT MAP (
	i => ww_B(1),
	o => \B[1]~input_o\);

-- Location: IOIBUF_X4_Y0_N1
\A[1]~input\ : cyclonev_io_ibuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	simulate_z_as => "z")
-- pragma translate_on
PORT MAP (
	i => ww_A(1),
	o => \A[1]~input_o\);

-- Location: LABCELL_X10_Y1_N54
\rca1|adder_1|ha_1|s\ : cyclonev_lcell_comb
-- Equation(s):
-- \rca1|adder_1|ha_1|s~combout\ = ( \B[1]~input_o\ & ( \A[1]~input_o\ & ( (\B[0]~input_o\ & \A[0]~input_o\) ) ) ) # ( !\B[1]~input_o\ & ( \A[1]~input_o\ & ( (!\B[0]~input_o\) # (!\A[0]~input_o\) ) ) ) # ( \B[1]~input_o\ & ( !\A[1]~input_o\ & ( 
-- (!\B[0]~input_o\) # (!\A[0]~input_o\) ) ) ) # ( !\B[1]~input_o\ & ( !\A[1]~input_o\ & ( (\B[0]~input_o\ & \A[0]~input_o\) ) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000001111111111111111000011111111111100000000000000001111",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datac => \ALT_INV_B[0]~input_o\,
	datad => \ALT_INV_A[0]~input_o\,
	datae => \ALT_INV_B[1]~input_o\,
	dataf => \ALT_INV_A[1]~input_o\,
	combout => \rca1|adder_1|ha_1|s~combout\);

-- Location: IOIBUF_X2_Y0_N58
\A[3]~input\ : cyclonev_io_ibuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	simulate_z_as => "z")
-- pragma translate_on
PORT MAP (
	i => ww_A(3),
	o => \A[3]~input_o\);

-- Location: IOIBUF_X8_Y0_N35
\B[2]~input\ : cyclonev_io_ibuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	simulate_z_as => "z")
-- pragma translate_on
PORT MAP (
	i => ww_B(2),
	o => \B[2]~input_o\);

-- Location: IOIBUF_X4_Y0_N18
\A[2]~input\ : cyclonev_io_ibuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	simulate_z_as => "z")
-- pragma translate_on
PORT MAP (
	i => ww_A(2),
	o => \A[2]~input_o\);

-- Location: LABCELL_X10_Y1_N36
\c1~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \c1~0_combout\ = ( \B[1]~input_o\ & ( \A[1]~input_o\ & ( (!\B[0]~input_o\ & (!\B[2]~input_o\ $ ((!\A[2]~input_o\)))) # (\B[0]~input_o\ & (!\A[0]~input_o\ & (!\B[2]~input_o\ $ (!\A[2]~input_o\)))) ) ) ) # ( !\B[1]~input_o\ & ( \A[1]~input_o\ & ( 
-- (\B[0]~input_o\ & (\A[0]~input_o\ & (!\B[2]~input_o\ $ (!\A[2]~input_o\)))) ) ) ) # ( \B[1]~input_o\ & ( !\A[1]~input_o\ & ( (\B[0]~input_o\ & (\A[0]~input_o\ & (!\B[2]~input_o\ $ (!\A[2]~input_o\)))) ) ) ) # ( !\B[1]~input_o\ & ( !\A[1]~input_o\ & ( 
-- (!\B[0]~input_o\ & (!\B[2]~input_o\ $ ((\A[2]~input_o\)))) # (\B[0]~input_o\ & (!\A[0]~input_o\ & (!\B[2]~input_o\ $ (\A[2]~input_o\)))) ) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "1100001110000010000000000001010000000000000101000011110000101000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \ALT_INV_B[0]~input_o\,
	datab => \ALT_INV_B[2]~input_o\,
	datac => \ALT_INV_A[2]~input_o\,
	datad => \ALT_INV_A[0]~input_o\,
	datae => \ALT_INV_B[1]~input_o\,
	dataf => \ALT_INV_A[1]~input_o\,
	combout => \c1~0_combout\);

-- Location: LABCELL_X10_Y1_N24
\rca1|adder_2|c_out\ : cyclonev_lcell_comb
-- Equation(s):
-- \rca1|adder_2|c_out~combout\ = ( \B[1]~input_o\ & ( \A[1]~input_o\ & ( (\B[2]~input_o\) # (\A[2]~input_o\) ) ) ) # ( !\B[1]~input_o\ & ( \A[1]~input_o\ & ( (!\A[2]~input_o\ & (\B[0]~input_o\ & (\B[2]~input_o\ & \A[0]~input_o\))) # (\A[2]~input_o\ & 
-- (((\B[0]~input_o\ & \A[0]~input_o\)) # (\B[2]~input_o\))) ) ) ) # ( \B[1]~input_o\ & ( !\A[1]~input_o\ & ( (!\A[2]~input_o\ & (\B[0]~input_o\ & (\B[2]~input_o\ & \A[0]~input_o\))) # (\A[2]~input_o\ & (((\B[0]~input_o\ & \A[0]~input_o\)) # 
-- (\B[2]~input_o\))) ) ) ) # ( !\B[1]~input_o\ & ( !\A[1]~input_o\ & ( (\A[2]~input_o\ & \B[2]~input_o\) ) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000001100000011000000110001011100000011000101110011111100111111",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \ALT_INV_B[0]~input_o\,
	datab => \ALT_INV_A[2]~input_o\,
	datac => \ALT_INV_B[2]~input_o\,
	datad => \ALT_INV_A[0]~input_o\,
	datae => \ALT_INV_B[1]~input_o\,
	dataf => \ALT_INV_A[1]~input_o\,
	combout => \rca1|adder_2|c_out~combout\);

-- Location: LABCELL_X10_Y1_N30
\rca2|adder_1|ha_0|s\ : cyclonev_lcell_comb
-- Equation(s):
-- \rca2|adder_1|ha_0|s~combout\ = ( \c1~0_combout\ & ( \rca1|adder_2|c_out~combout\ & ( !\rca1|adder_1|ha_1|s~combout\ $ (((!\B[3]~input_o\ & !\A[3]~input_o\))) ) ) ) # ( !\c1~0_combout\ & ( \rca1|adder_2|c_out~combout\ & ( !\rca1|adder_1|ha_1|s~combout\ ) 
-- ) ) # ( \c1~0_combout\ & ( !\rca1|adder_2|c_out~combout\ & ( !\rca1|adder_1|ha_1|s~combout\ $ (((!\B[3]~input_o\) # (!\A[3]~input_o\))) ) ) ) # ( !\c1~0_combout\ & ( !\rca1|adder_2|c_out~combout\ & ( !\rca1|adder_1|ha_1|s~combout\ $ (((!\B[3]~input_o\ & 
-- !\A[3]~input_o\))) ) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0011110011110000000011110011110011110000111100000011110011110000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datab => \ALT_INV_B[3]~input_o\,
	datac => \rca1|adder_1|ha_1|ALT_INV_s~combout\,
	datad => \ALT_INV_A[3]~input_o\,
	datae => \ALT_INV_c1~0_combout\,
	dataf => \rca1|adder_2|ALT_INV_c_out~combout\,
	combout => \rca2|adder_1|ha_0|s~combout\);

-- Location: LABCELL_X10_Y1_N51
\rca1|adder_1|c_out\ : cyclonev_lcell_comb
-- Equation(s):
-- \rca1|adder_1|c_out~combout\ = ( \A[1]~input_o\ & ( ((\B[0]~input_o\ & \A[0]~input_o\)) # (\B[1]~input_o\) ) ) # ( !\A[1]~input_o\ & ( (\B[0]~input_o\ & (\A[0]~input_o\ & \B[1]~input_o\)) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000101000000000000010100000101111111110000010111111111",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \ALT_INV_B[0]~input_o\,
	datac => \ALT_INV_A[0]~input_o\,
	datad => \ALT_INV_B[1]~input_o\,
	dataf => \ALT_INV_A[1]~input_o\,
	combout => \rca1|adder_1|c_out~combout\);

-- Location: LABCELL_X10_Y1_N6
\rca2|adder_2|ha_1|s\ : cyclonev_lcell_comb
-- Equation(s):
-- \rca2|adder_2|ha_1|s~combout\ = ( \rca1|adder_1|ha_1|s~combout\ & ( \rca1|adder_1|c_out~combout\ & ( !\A[2]~input_o\ $ (\B[2]~input_o\) ) ) ) # ( !\rca1|adder_1|ha_1|s~combout\ & ( \rca1|adder_1|c_out~combout\ & ( (!\A[2]~input_o\ & (!\B[2]~input_o\ $ 
-- (((\A[3]~input_o\) # (\B[3]~input_o\))))) # (\A[2]~input_o\ & (!\B[2]~input_o\ & ((\A[3]~input_o\) # (\B[3]~input_o\)))) ) ) ) # ( \rca1|adder_1|ha_1|s~combout\ & ( !\rca1|adder_1|c_out~combout\ & ( !\A[2]~input_o\ $ (!\B[2]~input_o\) ) ) ) # ( 
-- !\rca1|adder_1|ha_1|s~combout\ & ( !\rca1|adder_1|c_out~combout\ & ( (!\A[2]~input_o\ & ((!\B[3]~input_o\ & (\B[2]~input_o\ & !\A[3]~input_o\)) # (\B[3]~input_o\ & (!\B[2]~input_o\ & \A[3]~input_o\)))) # (\A[2]~input_o\ & (!\B[2]~input_o\ $ 
-- (((\A[3]~input_o\) # (\B[3]~input_o\))))) ) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0100100100100101010110100101101010010010010110101010010110100101",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \ALT_INV_A[2]~input_o\,
	datab => \ALT_INV_B[3]~input_o\,
	datac => \ALT_INV_B[2]~input_o\,
	datad => \ALT_INV_A[3]~input_o\,
	datae => \rca1|adder_1|ha_1|ALT_INV_s~combout\,
	dataf => \rca1|adder_1|ALT_INV_c_out~combout\,
	combout => \rca2|adder_2|ha_1|s~combout\);

-- Location: LABCELL_X10_Y1_N45
\rca2|adder_3|ha_1|s~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \rca2|adder_3|ha_1|s~0_combout\ = ( \c1~0_combout\ & ( \rca1|adder_2|c_out~combout\ & ( !\B[3]~input_o\ $ (\A[3]~input_o\) ) ) ) # ( !\c1~0_combout\ & ( \rca1|adder_2|c_out~combout\ & ( !\B[3]~input_o\ $ (!\A[3]~input_o\) ) ) ) # ( \c1~0_combout\ & ( 
-- !\rca1|adder_2|c_out~combout\ & ( !\B[3]~input_o\ $ (!\A[3]~input_o\) ) ) ) # ( !\c1~0_combout\ & ( !\rca1|adder_2|c_out~combout\ & ( (\B[3]~input_o\ & \A[3]~input_o\) ) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000001100000011001111000011110000111100001111001100001111000011",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datab => \ALT_INV_B[3]~input_o\,
	datac => \ALT_INV_A[3]~input_o\,
	datae => \ALT_INV_c1~0_combout\,
	dataf => \rca1|adder_2|ALT_INV_c_out~combout\,
	combout => \rca2|adder_3|ha_1|s~0_combout\);

-- Location: LABCELL_X10_Y1_N48
\c1~1\ : cyclonev_lcell_comb
-- Equation(s):
-- \c1~1_combout\ = ( \c1~0_combout\ & ( (!\A[3]~input_o\ & (\rca1|adder_2|c_out~combout\ & \B[3]~input_o\)) # (\A[3]~input_o\ & ((\B[3]~input_o\) # (\rca1|adder_2|c_out~combout\))) ) ) # ( !\c1~0_combout\ & ( ((\B[3]~input_o\) # 
-- (\rca1|adder_2|c_out~combout\)) # (\A[3]~input_o\) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0011111111111111001111111111111100000011001111110000001100111111",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datab => \ALT_INV_A[3]~input_o\,
	datac => \rca1|adder_2|ALT_INV_c_out~combout\,
	datad => \ALT_INV_B[3]~input_o\,
	dataf => \ALT_INV_c1~0_combout\,
	combout => \c1~1_combout\);

-- Location: LABCELL_X24_Y10_N3
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


