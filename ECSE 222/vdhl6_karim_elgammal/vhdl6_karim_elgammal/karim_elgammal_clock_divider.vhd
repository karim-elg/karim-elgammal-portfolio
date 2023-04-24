library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.ALL;

entity karim_elgammal_clock_divider is 
port( enable : in std_logic;
		reset  : in std_logic; 
		clk    : in std_logic; 
		en_out : out std_logic);
end karim_elgammal_clock_divider; 

architecture beh of karim_elgammal_clock_divider is 

signal temp : std_logic_vector(25 downto 0);

begin 

process (clk, reset, enable)
begin 
if reset <= '0' then 
	temp <= "10111110101111000001111111";
	elsif rising_edge(clk) then 
		if enable = '1' then 
			if temp =  "00000000000000000000000000" then 
				temp <= "10111110101111000001111111"; 
			else 
				temp <= std_logic_vector(unsigned(temp)-1);
			end if;
		end if;
	end if;
end process; 

en_out <= not( temp(0) or temp(1) or temp(2) or temp(3) or temp(4) or temp(5) or temp(6) or temp(7) or temp(8) or temp(9) or temp(10) or temp(11) 
or temp(12) or temp(13) or temp(14) or temp(15) or temp(16) or temp(17) or temp(18) or temp(19) or temp(20) or temp(21) or temp(22) or temp(23) or temp(24) 
or temp(25));

end beh; 

