library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.all;

entity myMUX is
 Port (A : in std_logic;
		 B : in std_logic;
		 S : in std_logic;
		 outY : out std_logic);
end myMUX;


architecture structlogicFunc of myMUX is

begin 

outY <= ((not S) and A) or (S and B);

end structlogicFunc; 


library IEEE; 
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all;


entity karim_elgammal_barrel_shifter_structural is 
Port (
	X : in std_logic_vector (3 downto 0);
	sel : in std_logic_vector (1 downto 0); 
	Y : out std_logic_vector (3 downto 0));
	
	
end karim_elgammal_barrel_shifter_structural;

architecture structBS of karim_elgammal_barrel_shifter_structural is

signal z0 : std_logic;
signal z1 : std_logic;
signal z2 : std_logic;
signal z3 : std_logic;


component myMUX 
	port (A : in std_logic;
		 B : in std_logic;
		 S : in std_logic;
		 outY : out std_logic);
		 end component;
	
	
	begin 
	
	mux0 : myMUX port map(A => X(0),
								 B => X(2),
								 S => sel(1),
								 outY => z0);
								 
	mux1 : myMUX port map(A => X(1),
								 B => X(3),
								 S => sel(1),
								 outY => z1);

	mux2 : myMUX port map(A => X(2),
								 B => X(0),
								 S => sel(1),
								 outY => z2);
								 
	mux3 : myMUX port map(A => X(3),
								 B => X(1),
								 S => sel(1),
								 outY => z3);
								 
	mux4 : myMUX port map(A => z0,
								 B => z3,
								 S => sel(0),
								 outY => Y(0));
								 
	mux5 : myMUX port map(A => z1,
								 B => z0,
								 S => sel(0),
								 outY => Y(1));
	
	mux6 : myMUX port map(A => z2,
								 B => z1,
								 S => sel(0),
								 outY => Y(2));
								 
	mux7 : myMUX port map(A => z3,
								 B => z2,
								 S => sel(0),
								 outY => Y(3));						
								
end structBS;
	
	
library IEEE;
use IEEE.std_logic_1164.ALL;
use IEEE.numeric_std.ALL; 

entity karim_elgammal_barrel_shifter_behavioral is 
	port( X : in std_logic_vector (3 downto 0); 
			sel : in std_logic_vector (1 downto 0);
			Y : out std_logic_vector (3 downto 0));
			
end karim_elgammal_barrel_shifter_behavioral;


architecture behlogicFunc of karim_elgammal_barrel_shifter_behavioral is 

begin
 
 with sel select 
 
		Y <= X(0) & X(1) & X(2) & X(3) when "00",
			  X(3) & X(0) & X(1) & X(2) when "10",
			  X(1) & X(2) & X(3) & X(0) when "11",
			  X(2) & X(3) & X(0) & X(1) when "01",
			  "XXXX" when others;
			 
			  
end behlogicFunc;
			  
		
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
 
