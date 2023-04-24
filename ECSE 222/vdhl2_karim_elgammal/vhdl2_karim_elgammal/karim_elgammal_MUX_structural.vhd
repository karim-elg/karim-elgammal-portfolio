library IEEE; 
use IEEE.std_logic_1164.all; 
use IEEE.numeric_std.all; 

entity karim_elgammal_MUX_structural is 
	port (A : in std_logic; 
			B : in std_logic; 
			S : in std_logic; 
			Y : out std_logic);
end karim_elgammal_MUX_structural; 

architecture structural of karim_elgammal_MUX_structural is 
		signal d0 : std_logic; 
		signal d1 : std_logic; 
		
			begin 
			d0 <= (not S) and A; 
			d1 <= S and B; 
			Y <= d0 or d1; 
end structural; 
