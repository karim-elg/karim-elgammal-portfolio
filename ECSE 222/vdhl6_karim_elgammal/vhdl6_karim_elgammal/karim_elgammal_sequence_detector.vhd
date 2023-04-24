library IEEE;
use ieee.std_logic_1164.all; 
use ieee.numeric_std.all;

entity karim_elgammal_sequence_detector is 
	Port( seq : in std_logic;
			enable: in std_logic;
			reset: in std_logic;
			clk: in std_logic; 
			cnt_1: out std_logic_vector(2 downto 0); --for 1011
			cnt_2: out std_logic_vector(2 downto 0)); --for 0010
end karim_elgammal_sequence_detector;

architecture beh of karim_elgammal_sequence_detector is 
	
component karim_elgammal_FSM 
	port( seq : in std_logic;
			enable: in std_logic; 
			reset : in std_logic;
			clk : in std_logic; 
			out_1 : out std_logic; 
			out_2 : out std_logic);
end component;

component karim_elgammal_counter 
Port (enable : in std_logic;
		reset : in std_logic; 
		clk : in std_logic;
		count : out std_logic_vector (2 downto 0)); 
end component;

signal counterdriver1 : std_logic; 
signal counterdriver2 : std_logic; 

begin 

seq_detect : karim_elgammal_FSM port map( seq => seq, 
													   enable => enable,
														reset => reset,
														clk => clk, 
														out_1 => counterdriver1,
														out_2 => counterdriver2);
														
counter1 : karim_elgammal_counter port map( enable => enable, 
															reset => reset,
															clk => counterdriver1, 
															count =>  cnt_1);
															
counter2 : karim_elgammal_counter port map( enable => enable,
															reset => reset,
															clk => counterdriver2,
															count => cnt_2);
end beh;
			