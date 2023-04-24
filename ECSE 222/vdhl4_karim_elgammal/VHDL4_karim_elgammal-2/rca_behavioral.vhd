library IEEE;
use IEEE.std_logic_1164.all;
USE IEEE.NUMERIC_STD.ALL;

entity rca_behavioral is 
	port ( A : in std_logic_vector (3 downto 0);
			 B : in std_logic_vector (3 downto 0);
			 S : out std_logic_vector (4 downto 0));
end rca_behavioral; 


architecture behRCA of rca_behavioral is 

signal temp0 : std_logic;
signal temp1 : std_logic;
signal temp2 : std_logic;
signal temp3 : std_logic; 

begin 

S(0) <= B(0) XOR A(0);
temp0 <= B(0) AND A(0); 


S(1) <= (B(1) XOR A(1)) XOR temp0;
temp1 <= (B(1) AND A(1)) OR (B(1) and temp0) OR (A(1) and temp0);

S(2) <= (B(2) XOR A(2)) XOR temp1;
temp2 <= (B(2) AND A(2)) OR (B(2) and temp1) OR (A(2) and temp1);

S(3) <= (B(3) XOR A(3)) XOR temp2;
S(4) <= (B(3) AND A(3)) OR (B(3) AND temp2) OR (A(3) AND temp2);

end behRCA;