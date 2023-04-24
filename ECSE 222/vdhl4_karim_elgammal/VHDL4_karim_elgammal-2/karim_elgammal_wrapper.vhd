library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.ALL;

entity seven_segment_decoder is 

port( code : in std_logic_vector(3 downto 0);
	   segments_out : out std_logic_vector(6 downto 0)); 
end seven_segment_decoder; 


architecture decoder of seven_segment_decoder is 
begin 

with code select 

segments_out <= 

"1000000" WHEN "0000", -- 0 
"1111001" WHEN "0001", -- 1
"0100100" WHEN "0010", -- 2 
"0110000" WHEN "0011", -- 3
"0011001" WHEN "0100", -- 4 
"0010010" WHEN "0101", -- 5
"0000010" WHEN "0110", -- 6 
"1111000" WHEN "0111", -- 7
"0000000" WHEN "1000", -- 8 
"0010000" WHEN "1001", -- 9
"1111111" WHEN others;

end decoder; 


library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.ALL;

entity karim_elgammal_wrapper is 
Port(A, B : in std_logic_vector(3 downto 0);
	decoded_A : out std_logic_vector(6 downto 0);
	decoded_B : out std_logic_vector(6 downto 0); 
	decoded_A_plus_B : out std_logic_vector(13 downto 0));
end karim_elgammal_wrapper; 


architecture wrapper of karim_elgammal_wrapper is 

component seven_segment_decoder 
	port( code : in std_logic_vector (3 downto 0);
			segments_out : out std_logic_vector(6 downto 0)); 
end component; 

component bcd_adder_structural
	port( A : in std_logic_vector(3 downto 0);
			B : in std_logic_vector(3 downto 0);
			S : out std_logic_vector(3 downto 0); 
			C : out std_logic);
end component;

signal overflow_signal : std_logic;
signal sum_signal : std_logic_vector(3 downto 0); 
signal decoded_overflow : std_logic_vector(6 downto 0);
signal decoded_sum : std_logic_vector(6 downto 0);


begin 

decode_a : seven_segment_decoder port map( code => A,
														 segments_out => decoded_A);
														 
decode_b : seven_segment_decoder port map( code => B,
														 segments_out => decoded_B);
														 

bcd_adder : bcd_adder_structural port map( A => A,
														 B => B, 
														 S => sum_signal,
														 C => overflow_signal);
														 
														 
decode_overflow : seven_segment_decoder port map( code => "000" & overflow_signal,
																  segments_out => decoded_overflow);
																  
decode_sum : seven_segment_decoder port map( code => sum_signal,
															segments_out => decoded_sum);
								
decoded_A_plus_B <= decoded_overflow & decoded_sum;

end wrapper; 
														 
			
			
