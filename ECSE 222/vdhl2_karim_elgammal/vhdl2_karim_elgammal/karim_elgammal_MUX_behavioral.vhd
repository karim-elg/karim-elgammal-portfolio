library IEEE; 
use IEEE.std_logic_1164.all; 
use IEEE.numeric_std.all; 

entity karim_elgammal_MUX_behavioral is 
	port (A : in std_logic; 
			B : in std_logic; 
			S : in std_logic; 
			Y : out std_logic);
end karim_elgammal_MUX_behavioral; 

architecture behavioral of karim_elgammal_MUX_behavioral is 
begin
	with S select
		Y <= A when '0',
			  B when others; 
end behavioral; 
		