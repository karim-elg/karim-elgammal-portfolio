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

signal temp : std_logic_vector(3 downto 0);

begin 

process (clk, reset, enable)
begin 
if reset <= '0' then 
	temp <= "1001";
	elsif rising_edge(clk) then 
		if enable = '1' then 
			if temp = "0000" then 
				temp <= "1001"; 
			else 
				temp <= std_logic_vector(unsigned(temp)-1);
			end if;
		end if;
	end if;
end process; 

en_out <= not( temp(0) or temp(1) or temp(2) or temp(3));

end beh; 

