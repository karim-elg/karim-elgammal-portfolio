library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.ALL; 

entity karim_elgammal_miniFSM is 
PORT (clock:    IN STD_LOGIC;
		enable:   in std_logic;
      P:        IN STD_LOGIC;
      reset:    IN STD_LOGIC;
      O :       OUT STD_LOGIC);
END karim_elgammal_miniFSM;

architecture beh of karim_elgammal_miniFSM is 
type state is (A0, A1, A2, A3, A4);

Signal currentState : state;


begin 	
process (clock, reset)

begin 
if (reset = '0') then 
	currentState <= A0;
	
	elsif falling_edge(clock) then 
	
if enable = '1' then 
	
	case currentState is 
		
		when A0 =>
			if P = '1' then 
				currentState <= A1;
					elsif P = '0' then
					currentState <= A0;
					end if; 
					
		when A1 => 
			if P = '1' then 
				currentState <= A1;
				elsif P = '0' then 
					currentState <= A2; 
					end if; 
			
		when A2 => 
			if P = '1' then 
			currentState <= A3;
				elsif P = '0' then 
					currentState <= A0;
					end if; 
					
		when A3 => 
			if P = '1' then 
				currentState <= A4;
					elsif P = '0' then 
						currentState <= A2; 
						end if;
						
		when A4 => 
			if P = '1' then 
				currentState <= A1;
					elsif P = '0' then 
						currentState <= A2; 
						end if; 
		
		when others => 
			currentState <= A0; 
			
end case;
end if;
end if;
end process; 
O <= '1' when currentState = A4 else '0';
end beh;

	
	
