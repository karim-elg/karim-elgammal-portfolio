library IEEE;
use ieee.std_logic_1164.all;
use ieee.std_logic_unsigned.all;

entity bcd_adder_behavioral is 
port ( A : in std_logic_vector (3 downto 0);
		 B : in std_logic_vector (3 downto 0);
		 S : out std_logic_vector (3 downto 0);
		 C : out std_logic);
end bcd_adder_behavioral; 

ARCHITECTURE behBCD of bcd_adder_behavioral is
signal z : std_logic_vector (4 downto 0);
signal temp : std_logic_vector (4 downto 0);
signal adjust : std_logic; 
begin 
 z <= ('0'& A) + B;
 adjust <= '1' when z > 9 else '0';
 temp <= z when (adjust = '0') else z + 6;
 S(0) <= temp(0);
 S(1) <= temp(1);
 S(2) <= temp(2);
 S(3) <= temp(3);
 C <= temp(4);
 
 
end behBCD;