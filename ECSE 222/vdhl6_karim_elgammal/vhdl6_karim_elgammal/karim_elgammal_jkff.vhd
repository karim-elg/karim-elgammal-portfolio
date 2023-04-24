library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.ALL; 

entity karim_elgammal_jkff is 

port ( clk : in std_logic;
		 J   : in std_logic;
		 K   : in std_logic; 
		 Q   : out std_logic);
end karim_elgammal_jkff; 

architecture beh of karim_elgammal_jkff is 
signal temp : std_logic; 

begin 
process (clk) 
	begin 
	if rising_edge(clk) then 
		if ( J = '0' AND K = '0') then 
			temp <= temp;
		elsif (J = '0' AND K = '1') then 
			temp <= '0';
		elsif (J = '1' AND K = '0') then 
			temp <= '1';
		elsif (J ='1' AND K = '1') then 
			temp <= NOT (temp);
			
		end if; 
	end if; 
	end process;
	Q <= temp;
	
end beh; 
		 