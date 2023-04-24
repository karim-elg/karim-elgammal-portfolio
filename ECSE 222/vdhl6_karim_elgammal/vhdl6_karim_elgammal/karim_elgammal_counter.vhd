library IEEE;
use IEEE.std_logic_1164.ALL; 
use IEEE.Numeric_std.ALL; 

entity karim_elgammal_jkff_Re is 
port ( reset : in std_logic;
			clk : in std_logic;
			J   : in std_logic; 
			K   : in std_logic;
			Q   : out std_logic);
end karim_elgammal_jkff_Re;

architecture beh of karim_elgammal_jkff_Re is 

signal jk : std_logic_vector (1 downto 0) := "00";
signal sigQ : std_logic := '0';

begin 

jk <= J&K ; 

process(reset, clk) 
begin 
if reset = '0' then sigQ <= '0';
elsif rising_edge(clk) then 
case(jk) is 
		when "00" => sigQ <= sigQ;
		when "01" => sigQ <= '0';
		when "10" => sigQ <= '1';
		when others => sigQ <= not (sigQ);
end case;
end if; 
end process; 

Q <= sigQ; 
end beh; 
 
 




library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.ALL;

entity karim_elgammal_counter is 
Port (enable : in std_logic;
		reset : in std_logic; 
		clk : in std_logic;
		count : out std_logic_vector (2 downto 0)); 
end karim_elgammal_counter;


architecture struct of  karim_elgammal_counter is 
component karim_elgammal_jkff_Re 
	Port ( reset : in std_logic;
			clk : in std_logic;
			J   : in std_logic; 
			K   : in std_logic;
			Q   : out std_logic); 
end component; 

signal temp : std_logic_vector (2 downto 0) := "000";
signal Etemp: std_logic_vector ( 2 downto 0) := "000";
begin 

j0 : karim_elgammal_jkff_Re port map( reset => reset,
												  clk => clk,
												  J => enable, 
												  K => enable,
												  Q => temp(2));
												
Etemp(2) <= temp(2) AND enable;

												  
j1 : karim_elgammal_jkff_Re port map ( reset => reset,
													clk => clk,
													J => Etemp(2),
													K => Etemp(2),
													Q => temp(1));
													
Etemp(1) <= temp(1) AND temp(2);
Etemp(0) <= Etemp(1) AND enable; 

j2 : karim_elgammal_jkff_Re port map (reset => reset, 
													clk => clk, 
													J => Etemp(0), 
													K => Etemp(0),
													Q => temp(0));
count(2) <= temp(0); 
count(1) <= temp(1); 
count(0) <= temp(2); 

end struct;
			
				