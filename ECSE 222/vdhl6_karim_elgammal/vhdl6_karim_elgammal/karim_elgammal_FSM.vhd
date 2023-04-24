library ieee;
use ieee.std_logic_1164.all; 
use ieee.numeric_std.all; 

entity karim_elgammal_FSM is 
	port( seq : in std_logic;
			enable: in std_logic; 
			reset : in std_logic;
			clk : in std_logic; 
			out_1 : out std_logic; 
			out_2 : out std_logic);
end karim_elgammal_FSM; 

architecture beh of karim_elgammal_FSM is 

component karim_elgammal_miniFSM --for 1011
Port (clock : 	in std_logic;
		enable : in std_logic;
		P : 		in std_logic; 
		reset : 	in std_logic;
		O : 		out std_logic);
end component; 

component karim_elgammal_miniFSM2 -- for 0010
Port (clock : 	in std_logic;
		enable : in std_logic;
		P : 		in std_logic; 
		reset : 	in std_logic;
		O : 		out std_logic);
end component; 

begin

miniFSM1 : karim_elgammal_miniFSM port map( clock => clk, 
															enable => enable,
															P => seq,
															reset => reset,
															O => out_1);
															
miniFSM2 : karim_elgammal_miniFSM2 port map ( clock => clk,
															 enable => enable,
															 P => seq,
															 reset => reset,
															 O => out_2);
															 
end beh;


