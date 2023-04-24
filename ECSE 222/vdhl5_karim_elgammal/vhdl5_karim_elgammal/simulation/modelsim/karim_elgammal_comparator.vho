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

-- VENDOR "Altera"
-- PROGRAM "Quartus Prime"
-- VERSION "Version 16.1.0 Build 196 10/24/2016 SJ Lite Edition"

-- DATE "12/02/2021 19:32:55"

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
	enable : IN std_logic;
	reset : IN std_logic;
	clk : IN std_logic;
	HEX0 : BUFFER std_logic_vector(6 DOWNTO 0)
	);
END karim_elgammal_wrapper;

-- Design Ports Information
-- HEX0[0]	=>  Location: PIN_AA30,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- HEX0[1]	=>  Location: PIN_AC28,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- HEX0[2]	=>  Location: PIN_V25,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- HEX0[3]	=>  Location: PIN_AD29,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- HEX0[4]	=>  Location: PIN_AB28,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- HEX0[5]	=>  Location: PIN_AB30,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- HEX0[6]	=>  Location: PIN_AA28,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- enable	=>  Location: PIN_W25,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- reset	=>  Location: PIN_AC29,	 I/O Standard: 2.5 V,	 Current Strength: Default
-- clk	=>  Location: PIN_Y27,	 I/O Standard: 2.5 V,	 Current Strength: Default


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
SIGNAL ww_enable : std_logic;
SIGNAL ww_reset : std_logic;
SIGNAL ww_clk : std_logic;
SIGNAL ww_HEX0 : std_logic_vector(6 DOWNTO 0);
SIGNAL \~QUARTUS_CREATED_GND~I_combout\ : std_logic;
SIGNAL \clk~input_o\ : std_logic;
SIGNAL \clk~inputCLKENA0_outclk\ : std_logic;
SIGNAL \clkdiv|temp[0]~4_combout\ : std_logic;
SIGNAL \reset~input_o\ : std_logic;
SIGNAL \enable~input_o\ : std_logic;
SIGNAL \clkdiv|Add0~0_combout\ : std_logic;
SIGNAL \clkdiv|temp~1_combout\ : std_logic;
SIGNAL \clkdiv|temp~0_combout\ : std_logic;
SIGNAL \clkdiv|en_out~0_combout\ : std_logic;
SIGNAL \cntr|j0|sigQ~0_combout\ : std_logic;
SIGNAL \cntr|j0|sigQ~q\ : std_logic;
SIGNAL \cntr|j1|sigQ~0_combout\ : std_logic;
SIGNAL \cntr|j1|sigQ~q\ : std_logic;
SIGNAL \cntr|j2|sigQ~0_combout\ : std_logic;
SIGNAL \cntr|j2|sigQ~q\ : std_logic;
SIGNAL \dcdr|Mux6~0_combout\ : std_logic;
SIGNAL \dcdr|Mux5~0_combout\ : std_logic;
SIGNAL \dcdr|Mux4~0_combout\ : std_logic;
SIGNAL \dcdr|Mux3~0_combout\ : std_logic;
SIGNAL \dcdr|Mux2~0_combout\ : std_logic;
SIGNAL \dcdr|Mux1~0_combout\ : std_logic;
SIGNAL \dcdr|Mux0~0_combout\ : std_logic;
SIGNAL \clkdiv|temp\ : std_logic_vector(3 DOWNTO 0);
SIGNAL \ALT_INV_enable~input_o\ : std_logic;
SIGNAL \clkdiv|ALT_INV_temp\ : std_logic_vector(3 DOWNTO 0);
SIGNAL \cntr|j2|ALT_INV_sigQ~q\ : std_logic;
SIGNAL \cntr|j0|ALT_INV_sigQ~q\ : std_logic;
SIGNAL \cntr|j1|ALT_INV_sigQ~q\ : std_logic;

BEGIN

ww_enable <= enable;
ww_reset <= reset;
ww_clk <= clk;
HEX0 <= ww_HEX0;
ww_devoe <= devoe;
ww_devclrn <= devclrn;
ww_devpor <= devpor;
\ALT_INV_enable~input_o\ <= NOT \enable~input_o\;
\clkdiv|ALT_INV_temp\(1) <= NOT \clkdiv|temp\(1);
\clkdiv|ALT_INV_temp\(0) <= NOT \clkdiv|temp\(0);
\clkdiv|ALT_INV_temp\(2) <= NOT \clkdiv|temp\(2);
\clkdiv|ALT_INV_temp\(3) <= NOT \clkdiv|temp\(3);
\cntr|j2|ALT_INV_sigQ~q\ <= NOT \cntr|j2|sigQ~q\;
\cntr|j0|ALT_INV_sigQ~q\ <= NOT \cntr|j0|sigQ~q\;
\cntr|j1|ALT_INV_sigQ~q\ <= NOT \cntr|j1|sigQ~q\;

-- Location: IOOBUF_X89_Y21_N22
\HEX0[0]~output\ : cyclonev_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false",
	shift_series_termination_control => "false")
-- pragma translate_on
PORT MAP (
	i => \dcdr|Mux6~0_combout\,
	devoe => ww_devoe,
	o => ww_HEX0(0));

-- Location: IOOBUF_X89_Y20_N79
\HEX0[1]~output\ : cyclonev_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false",
	shift_series_termination_control => "false")
-- pragma translate_on
PORT MAP (
	i => \dcdr|Mux5~0_combout\,
	devoe => ww_devoe,
	o => ww_HEX0(1));

-- Location: IOOBUF_X89_Y20_N62
\HEX0[2]~output\ : cyclonev_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false",
	shift_series_termination_control => "false")
-- pragma translate_on
PORT MAP (
	i => \dcdr|Mux4~0_combout\,
	devoe => ww_devoe,
	o => ww_HEX0(2));

-- Location: IOOBUF_X89_Y23_N56
\HEX0[3]~output\ : cyclonev_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false",
	shift_series_termination_control => "false")
-- pragma translate_on
PORT MAP (
	i => \dcdr|Mux3~0_combout\,
	devoe => ww_devoe,
	o => ww_HEX0(3));

-- Location: IOOBUF_X89_Y21_N39
\HEX0[4]~output\ : cyclonev_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false",
	shift_series_termination_control => "false")
-- pragma translate_on
PORT MAP (
	i => \dcdr|Mux2~0_combout\,
	devoe => ww_devoe,
	o => ww_HEX0(4));

-- Location: IOOBUF_X89_Y21_N5
\HEX0[5]~output\ : cyclonev_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false",
	shift_series_termination_control => "false")
-- pragma translate_on
PORT MAP (
	i => \dcdr|Mux1~0_combout\,
	devoe => ww_devoe,
	o => ww_HEX0(5));

-- Location: IOOBUF_X89_Y21_N56
\HEX0[6]~output\ : cyclonev_io_obuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	open_drain_output => "false",
	shift_series_termination_control => "false")
-- pragma translate_on
PORT MAP (
	i => \dcdr|Mux0~0_combout\,
	devoe => ww_devoe,
	o => ww_HEX0(6));

-- Location: IOIBUF_X89_Y25_N21
\clk~input\ : cyclonev_io_ibuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	simulate_z_as => "z")
-- pragma translate_on
PORT MAP (
	i => ww_clk,
	o => \clk~input_o\);

-- Location: CLKCTRL_G10
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

-- Location: LABCELL_X88_Y21_N24
\clkdiv|temp[0]~4\ : cyclonev_lcell_comb
-- Equation(s):
-- \clkdiv|temp[0]~4_combout\ = ( !\clkdiv|temp\(0) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "1111111111111111111111111111111100000000000000000000000000000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataf => \clkdiv|ALT_INV_temp\(0),
	combout => \clkdiv|temp[0]~4_combout\);

-- Location: IOIBUF_X89_Y20_N95
\reset~input\ : cyclonev_io_ibuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	simulate_z_as => "z")
-- pragma translate_on
PORT MAP (
	i => ww_reset,
	o => \reset~input_o\);

-- Location: IOIBUF_X89_Y20_N44
\enable~input\ : cyclonev_io_ibuf
-- pragma translate_off
GENERIC MAP (
	bus_hold => "false",
	simulate_z_as => "z")
-- pragma translate_on
PORT MAP (
	i => ww_enable,
	o => \enable~input_o\);

-- Location: FF_X88_Y21_N29
\clkdiv|temp[0]\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk~inputCLKENA0_outclk\,
	asdata => \clkdiv|temp[0]~4_combout\,
	clrn => \reset~input_o\,
	sload => VCC,
	ena => \enable~input_o\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \clkdiv|temp\(0));

-- Location: LABCELL_X88_Y21_N48
\clkdiv|Add0~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \clkdiv|Add0~0_combout\ = ( \clkdiv|temp\(2) & ( \clkdiv|temp\(3) ) ) # ( !\clkdiv|temp\(2) & ( !\clkdiv|temp\(3) $ (((!\clkdiv|temp\(0)) # (\clkdiv|temp\(1)))) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0101000010101111010100001010111100000000111111110000000011111111",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \clkdiv|ALT_INV_temp\(0),
	datac => \clkdiv|ALT_INV_temp\(1),
	datad => \clkdiv|ALT_INV_temp\(3),
	dataf => \clkdiv|ALT_INV_temp\(2),
	combout => \clkdiv|Add0~0_combout\);

-- Location: FF_X88_Y21_N50
\clkdiv|temp[3]\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk~inputCLKENA0_outclk\,
	d => \clkdiv|Add0~0_combout\,
	clrn => \reset~input_o\,
	ena => \enable~input_o\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \clkdiv|temp\(3));

-- Location: LABCELL_X88_Y21_N33
\clkdiv|temp~1\ : cyclonev_lcell_comb
-- Equation(s):
-- \clkdiv|temp~1_combout\ = ( \clkdiv|temp\(2) & ( !\clkdiv|temp\(0) $ (!\clkdiv|temp\(1)) ) ) # ( !\clkdiv|temp\(2) & ( (!\clkdiv|temp\(0) & ((\clkdiv|temp\(1)))) # (\clkdiv|temp\(0) & (!\clkdiv|temp\(3) & !\clkdiv|temp\(1))) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000101011110000000010101111000000001111111100000000111111110000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \clkdiv|ALT_INV_temp\(3),
	datac => \clkdiv|ALT_INV_temp\(0),
	datad => \clkdiv|ALT_INV_temp\(1),
	dataf => \clkdiv|ALT_INV_temp\(2),
	combout => \clkdiv|temp~1_combout\);

-- Location: FF_X88_Y21_N35
\clkdiv|temp[1]\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk~inputCLKENA0_outclk\,
	d => \clkdiv|temp~1_combout\,
	clrn => \reset~input_o\,
	ena => \enable~input_o\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \clkdiv|temp\(1));

-- Location: LABCELL_X88_Y21_N54
\clkdiv|temp~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \clkdiv|temp~0_combout\ = ( \clkdiv|temp\(3) & ( (\clkdiv|temp\(2) & ((!\clkdiv|temp\(0)) # (\clkdiv|temp\(1)))) ) ) # ( !\clkdiv|temp\(3) & ( !\clkdiv|temp\(2) $ (((!\clkdiv|temp\(0)) # (\clkdiv|temp\(1)))) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0101000010101111010100001010111100000000101011110000000010101111",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \clkdiv|ALT_INV_temp\(0),
	datac => \clkdiv|ALT_INV_temp\(1),
	datad => \clkdiv|ALT_INV_temp\(2),
	dataf => \clkdiv|ALT_INV_temp\(3),
	combout => \clkdiv|temp~0_combout\);

-- Location: FF_X88_Y21_N56
\clkdiv|temp[2]\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clk~inputCLKENA0_outclk\,
	d => \clkdiv|temp~0_combout\,
	clrn => \reset~input_o\,
	ena => \enable~input_o\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \clkdiv|temp\(2));

-- Location: LABCELL_X88_Y21_N6
\clkdiv|en_out~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \clkdiv|en_out~0_combout\ = LCELL(( \clkdiv|temp\(0) & ( \clkdiv|temp\(3) & ( (!\clkdiv|temp\(2) & !\clkdiv|temp\(1)) ) ) ))

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000000000000000000000000000000000001010000010100000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \clkdiv|ALT_INV_temp\(2),
	datac => \clkdiv|ALT_INV_temp\(1),
	datae => \clkdiv|ALT_INV_temp\(0),
	dataf => \clkdiv|ALT_INV_temp\(3),
	combout => \clkdiv|en_out~0_combout\);

-- Location: LABCELL_X88_Y21_N18
\cntr|j0|sigQ~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \cntr|j0|sigQ~0_combout\ = ( !\cntr|j0|sigQ~q\ )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "1111111111111111111111111111111100000000000000000000000000000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataf => \cntr|j0|ALT_INV_sigQ~q\,
	combout => \cntr|j0|sigQ~0_combout\);

-- Location: FF_X88_Y21_N44
\cntr|j0|sigQ\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clkdiv|en_out~0_combout\,
	asdata => \cntr|j0|sigQ~0_combout\,
	clrn => \reset~input_o\,
	sload => VCC,
	ena => \enable~input_o\,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \cntr|j0|sigQ~q\);

-- Location: LABCELL_X88_Y21_N12
\cntr|j1|sigQ~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \cntr|j1|sigQ~0_combout\ = ( \cntr|j1|sigQ~q\ & ( \cntr|j0|sigQ~q\ & ( !\enable~input_o\ ) ) ) # ( !\cntr|j1|sigQ~q\ & ( \cntr|j0|sigQ~q\ & ( \enable~input_o\ ) ) ) # ( \cntr|j1|sigQ~q\ & ( !\cntr|j0|sigQ~q\ ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000111111111111111100110011001100111100110011001100",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datab => \ALT_INV_enable~input_o\,
	datae => \cntr|j1|ALT_INV_sigQ~q\,
	dataf => \cntr|j0|ALT_INV_sigQ~q\,
	combout => \cntr|j1|sigQ~0_combout\);

-- Location: FF_X88_Y21_N23
\cntr|j1|sigQ\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clkdiv|en_out~0_combout\,
	asdata => \cntr|j1|sigQ~0_combout\,
	clrn => \reset~input_o\,
	sload => VCC,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \cntr|j1|sigQ~q\);

-- Location: LABCELL_X88_Y21_N36
\cntr|j2|sigQ~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \cntr|j2|sigQ~0_combout\ = ( \cntr|j0|sigQ~q\ & ( !\cntr|j2|sigQ~q\ $ (((!\cntr|j1|sigQ~q\) # (!\enable~input_o\))) ) ) # ( !\cntr|j0|sigQ~q\ & ( \cntr|j2|sigQ~q\ ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0011001100110011001100110011001100110011001111000011001100111100",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datab => \cntr|j2|ALT_INV_sigQ~q\,
	datac => \cntr|j1|ALT_INV_sigQ~q\,
	datad => \ALT_INV_enable~input_o\,
	dataf => \cntr|j0|ALT_INV_sigQ~q\,
	combout => \cntr|j2|sigQ~0_combout\);

-- Location: FF_X88_Y21_N2
\cntr|j2|sigQ\ : dffeas
-- pragma translate_off
GENERIC MAP (
	is_wysiwyg => "true",
	power_up => "low")
-- pragma translate_on
PORT MAP (
	clk => \clkdiv|en_out~0_combout\,
	asdata => \cntr|j2|sigQ~0_combout\,
	clrn => \reset~input_o\,
	sload => VCC,
	devclrn => ww_devclrn,
	devpor => ww_devpor,
	q => \cntr|j2|sigQ~q\);

-- Location: LABCELL_X88_Y21_N3
\dcdr|Mux6~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \dcdr|Mux6~0_combout\ = ( !\cntr|j2|sigQ~q\ & ( \cntr|j0|sigQ~q\ & ( !\cntr|j1|sigQ~q\ ) ) ) # ( \cntr|j2|sigQ~q\ & ( !\cntr|j0|sigQ~q\ & ( !\cntr|j1|sigQ~q\ ) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000000000000000111100001111000011110000111100000000000000000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datac => \cntr|j1|ALT_INV_sigQ~q\,
	datae => \cntr|j2|ALT_INV_sigQ~q\,
	dataf => \cntr|j0|ALT_INV_sigQ~q\,
	combout => \dcdr|Mux6~0_combout\);

-- Location: LABCELL_X88_Y21_N30
\dcdr|Mux5~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \dcdr|Mux5~0_combout\ = ( \cntr|j0|sigQ~q\ & ( (!\cntr|j1|sigQ~q\ & \cntr|j2|sigQ~q\) ) ) # ( !\cntr|j0|sigQ~q\ & ( (\cntr|j1|sigQ~q\ & \cntr|j2|sigQ~q\) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0000001100000011000000110000001100001100000011000000110000001100",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datab => \cntr|j1|ALT_INV_sigQ~q\,
	datac => \cntr|j2|ALT_INV_sigQ~q\,
	dataf => \cntr|j0|ALT_INV_sigQ~q\,
	combout => \dcdr|Mux5~0_combout\);

-- Location: LABCELL_X88_Y21_N51
\dcdr|Mux4~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \dcdr|Mux4~0_combout\ = ( !\cntr|j0|sigQ~q\ & ( (\cntr|j1|sigQ~q\ & !\cntr|j2|sigQ~q\) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0011000000110000001100000011000000000000000000000000000000000000",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datab => \cntr|j1|ALT_INV_sigQ~q\,
	datac => \cntr|j2|ALT_INV_sigQ~q\,
	dataf => \cntr|j0|ALT_INV_sigQ~q\,
	combout => \dcdr|Mux4~0_combout\);

-- Location: LABCELL_X88_Y21_N27
\dcdr|Mux3~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \dcdr|Mux3~0_combout\ = ( \cntr|j0|sigQ~q\ & ( !\cntr|j2|sigQ~q\ $ (\cntr|j1|sigQ~q\) ) ) # ( !\cntr|j0|sigQ~q\ & ( (\cntr|j2|sigQ~q\ & !\cntr|j1|sigQ~q\) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0101000001010000010100000101000010100101101001011010010110100101",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \cntr|j2|ALT_INV_sigQ~q\,
	datac => \cntr|j1|ALT_INV_sigQ~q\,
	dataf => \cntr|j0|ALT_INV_sigQ~q\,
	combout => \dcdr|Mux3~0_combout\);

-- Location: LABCELL_X88_Y21_N39
\dcdr|Mux2~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \dcdr|Mux2~0_combout\ = ( \cntr|j0|sigQ~q\ ) # ( !\cntr|j0|sigQ~q\ & ( (\cntr|j2|sigQ~q\ & !\cntr|j1|sigQ~q\) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0101000001010000010100000101000011111111111111111111111111111111",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \cntr|j2|ALT_INV_sigQ~q\,
	datac => \cntr|j1|ALT_INV_sigQ~q\,
	dataf => \cntr|j0|ALT_INV_sigQ~q\,
	combout => \dcdr|Mux2~0_combout\);

-- Location: LABCELL_X88_Y21_N21
\dcdr|Mux1~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \dcdr|Mux1~0_combout\ = (!\cntr|j2|sigQ~q\ & ((\cntr|j1|sigQ~q\) # (\cntr|j0|sigQ~q\))) # (\cntr|j2|sigQ~q\ & (\cntr|j0|sigQ~q\ & \cntr|j1|sigQ~q\))

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "0010001010111011001000101011101100100010101110110010001010111011",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	dataa => \cntr|j2|ALT_INV_sigQ~q\,
	datab => \cntr|j0|ALT_INV_sigQ~q\,
	datad => \cntr|j1|ALT_INV_sigQ~q\,
	combout => \dcdr|Mux1~0_combout\);

-- Location: LABCELL_X88_Y21_N57
\dcdr|Mux0~0\ : cyclonev_lcell_comb
-- Equation(s):
-- \dcdr|Mux0~0_combout\ = ( \cntr|j0|sigQ~q\ & ( !\cntr|j1|sigQ~q\ $ (\cntr|j2|sigQ~q\) ) ) # ( !\cntr|j0|sigQ~q\ & ( (!\cntr|j1|sigQ~q\ & !\cntr|j2|sigQ~q\) ) )

-- pragma translate_off
GENERIC MAP (
	extended_lut => "off",
	lut_mask => "1100000011000000110000001100000011000011110000111100001111000011",
	shared_arith => "off")
-- pragma translate_on
PORT MAP (
	datab => \cntr|j1|ALT_INV_sigQ~q\,
	datac => \cntr|j2|ALT_INV_sigQ~q\,
	dataf => \cntr|j0|ALT_INV_sigQ~q\,
	combout => \dcdr|Mux0~0_combout\);

-- Location: LABCELL_X13_Y28_N0
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


