 library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.ALL;
use IEEE.std_logic_unsigned.ALL;

entity karim_elgammal_comparator is 
Port (A, B : in std_logic_vector (3 downto 0);
		AgtBplusOne : out std_logic; 
		AgteBplusOne : out std_logic;
		AltBplusOne : out std_logic; 
		AlteBplusOne : out std_logic;
		AeqBplusOne : out std_logic; 
		overflow : out std_logic);
end karim_elgammal_comparator;

architecture beh of karim_elgammal_comparator is 
begin 	
	process (A, B) 
		begin 
			overflow <= '0';	
	if B = 15 then 
	overflow <= '1';
	AgtBplusOne <= '0';
	AgteBplusOne <= '0';
	AltBplusOne <= '0';
	AlteBplusOne <= '0';
	AeqBplusOne <= '0';
	
	elsif A = (B + 1) then 
	overflow <= '0';
	AgtBplusOne <= '0';
	AgteBplusOne <= '1';
	AltBplusOne <= '0';
	AlteBplusOne <= '1';
	AeqBplusOne <= '1';
	
	elsif A > (B + 1) then 
	AgtBplusOne <= '1';
	AgteBplusOne <= '1';
	overflow <= '0';	
	AltBplusOne <= '0';
	AlteBplusOne <= '0';
	AeqBplusOne <= '0';
	
	elsif A < (B + 1) then 
	AgtBplusOne <= '0';
	AgteBplusOne <= '0';
	overflow <= '0';	
	AltBplusOne <= '1';
	AlteBplusOne <= '1';
	AeqBplusOne <= '0';
	end if;
	
	end process; 
end beh; 
	
	