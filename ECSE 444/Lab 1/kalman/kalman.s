.syntax unified
.align 16
.section .text, "x"
.global kalman

// Take input array R0, outputarray R1, struct R2, length R3
kalman:
	VPUSH {S1-S6}
	MOV R1, R0 // Move address of struct into r1
	VLDM R1, {S1-S5} // Load q,r,p,k,x into S1-S5
	VADD.F32 S3, S3, S1 // p = p+q
	VADD.F32 S6, S2, S3 // P+r and store in register
	VDIV.F32 S4, S3, S6 // k = p/(p+r)
	VSUB.F32 S6, S0, S5 // measurement - x
	VMLA.F32 S5, S6, S4 // x = x + k*(measurement-x)
	VMLS.F32 S3, S3, S4 // p = (1-k)*p
	VMRS R0, FPSCR // Read fpscr status
	ANDS R0, R0, 0xF // AND with error bits
	BNE exit // If error detected, don't update struct
	VSTM R1, {S1-S5} // q,r,p,k,x changed in struct
exit:
	VPOP {S1-S6}
	BX LR
