library IEEE;
use IEEE.STD_LOGIC_1164.ALL; 
use IEEE.NUMERIC_STD.ALL; 

entity half_adder is
port (a: in std_logic; 
		b: in std_logic;
		s: out std_logic; 
		c: out std_logic);
end half_adder;

architecture strucHA of half_adder is 

begin 

s <= a XOR b; 
c <= a AND b;

end strucHA;

library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
USE IEEE.NUMERIC_STD.ALL;

ENTITY full_adder is 
	port( a : in std_logic;
			b : in std_logic;
			c_in : in std_logic; 
			s : out std_logic;
			c_out : out std_logic);
end full_adder; 

architecture strucFA of full_adder is 

signal temp0 : std_logic;
signal temp1 : std_logic;
signal temp2 : std_logic;

component half_adder 
	port ( a : in std_logic;
			 b : in std_logic;
			 s : out std_logic;
			 c : out std_logic);
		end component; 
		
begin 
		ha_0 : half_adder port map( a => a,
											 b => b,
											 s => temp0,
											 c => temp1);
			
		ha_1 : half_adder port map( a => c_in,
											 b => temp0,
											 s => s,
											 c => temp2);
											 
		c_out <= temp1 OR temp2;
		
end strucFA;


library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
USE IEEE.NUMERIC_STD.ALL;

entity rca_structural is 
	port( A: in std_logic_vector (3 downto 0);
			B: in std_logic_vector (3 downto 0);
			S: out std_logic_vector (4 downto 0));
end rca_structural;

architecture strucRCA of rca_structural is 
	signal temp0: std_logic;
	signal temp1: std_logic;
	signal temp2: std_logic;
	
	component half_adder 
	port ( a : in std_logic;
			 b : in std_logic;
			 s : out std_logic;
			 c : out std_logic);
		end component; 
		
	component full_adder 
	port( a : in std_logic;
			b : in std_logic;
			c_in : in std_logic; 
			s : out std_logic;
			c_out : out std_logic);
		end component;
begin	
	adder_0 : half_adder port map( a => B(0),
											 b => A(0),
											 s => S(0),
											 c => temp0);
											 
	adder_1 : full_adder port map( a => B(1),
											 b => A(1),
											 c_in => temp0,
											 s => S(1),
											 c_out => temp1);
											 
	adder_2 : full_adder port map( a => B(2),
											 b => A(2),
											 c_in => temp1,
											 s => S(2),
											 c_out => temp2);
											 
	adder_3 : full_adder port map( a => B(3),
											 b => A(3),
											 c_in => temp2,
											 s => S(3),
											 c_out => S(4));
											
end strucRCA;

									

library IEEE;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

entity bcd_adder_structural is 
port ( A : in std_logic_vector (3 downto 0);
		 B : in std_logic_vector (3 downto 0);
		 S : out std_logic_vector (3 downto 0);
		 C : out std_logic);
end bcd_adder_structural; 


architecture strucBCD of bcd_adder_structural is 

signal z0 : std_logic;
signal z1 : std_logic;
signal z2 : std_logic; 
signal z3 : std_logic; 
signal c0 : std_logic; 
signal c1 : std_logic;
signal placeholder :std_logic; 
component rca_structural 
	port( A: in std_logic_vector (3 downto 0);
			B: in std_logic_vector (3 downto 0);
			S: out std_logic_vector (4 downto 0));
end component; 
begin
rca1 : rca_structural port map( A => A,
										  B => B, 
										  S(0) => z0,
										  S(1) => z1,
										  S(2) => z2,
										  S(3) => z3,
										  S(4) => c0);
										  
										  
c1 <= (z3 AND z1) OR (z2 AND z3) OR c0;

										  
rca2 : rca_structural port map( A(0) => z0,
										  A(1) => z1,
										  A(2) => z2,
										  A(3) => z3,
										  B(0) => '0',
										  B(1) => c1,
										  B(2) => c1,
										  B(3) => '0',
										  S(0) => S(0),
										  S(1) => S(1),
										  S(2) => S(2),
										  S(3) => S(3), 
										  S(4) => placeholder); 
C <= c1;
	
end strucBCD;				

