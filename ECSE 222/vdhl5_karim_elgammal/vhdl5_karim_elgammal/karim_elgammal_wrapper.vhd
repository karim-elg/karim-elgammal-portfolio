library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

entity seven_segment_decoder is
port ( code : in std_logic_vector (2 downto 0);
segments_out : out std_logic_vector (6 downto 0));
end seven_segment_decoder;

architecture beh of seven_segment_decoder is
begin
WITH code SELECT
segments_out <=
"1000000" WHEN "000", -- 0	
"1111001" WHEN "001", -- 1
"0100100" WHEN "010", -- 2
"0110000" WHEN "011", -- 3
"0011001" WHEN "100", -- 4
"0010010" WHEN "101", -- 5
"0000010" WHEN "110", -- 6
"1111000" WHEN "111", -- 7
"1111111" WHEN others;
end beh;



library IEEE; 
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all; 

entity karim_elgammal_wrapper is 
port (enable : in std_logic;
		reset  : in std_logic;
		clk    : in std_logic; 
		HEX0   : out std_logic_vector(6 downto 0));
end karim_elgammal_wrapper; 


architecture struct of karim_elgammal_wrapper is 

component karim_elgammal_counter 
Port (enable : in std_logic;
		reset : in std_logic; 
		clk : in std_logic;
		count : out std_logic_vector (2 downto 0));
end component; 

component karim_elgammal_clock_divider 
port( enable : in std_logic;
		reset  : in std_logic; 
		clk    : in std_logic; 
		en_out : out std_logic);
end component; 

component seven_segment_decoder 
port ( code : in std_logic_vector (2 downto 0);
segments_out : out std_logic_vector (6 downto 0));
end component; 

signal clkdiv_to_counter : std_logic; 
signal counter_to_decoder : std_logic_vector(2 downto 0); 

begin 

clkdiv : karim_elgammal_clock_divider port map(enable => enable,
																 reset => reset, 
																 clk => clk,
																 en_out => clkdiv_to_counter);
																 
cntr : karim_elgammal_counter port map(enable => enable,
													reset => reset,
													clk => clkdiv_to_counter,
													count => counter_to_decoder); 
										
dcdr : seven_segment_decoder port map(code => counter_to_decoder,
												  segments_out => HEX0);
end struct; 