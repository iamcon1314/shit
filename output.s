  .section .data
n:
  .word 0
p:
  .word 0
k:
  .word 0
i:
  .word 0
  .section .rodata
.str.0:
  .string "<< "
.str.3:
  .string " "
.str.4:
  .string ">> "
.str.2:
  .string ") "
.str.1:
  .string "("
  .text
  .globl main
main:
.L0:
  li t0, -228
  add sp, sp, t0
  sw ra, 0(sp)
  li t0, 4
  add t0, sp, t0
  sw t0, 12(sp)
  li t0, 8
  add t0, sp, t0
  sw t0, 16(sp)
  call getInt
  mv t0, a0
  sw t0, 20(sp)
  lui t1, %hi(n)
  addi t1, t1, %lo(n)
  lw t0, 20(sp)
  sw t0, 0(t1)
  call getInt
  mv t0, a0
  sw t0, 24(sp)
  lui t1, %hi(p)
  addi t1, t1, %lo(p)
  lw t0, 24(sp)
  sw t0, 0(t1)
  call getInt
  mv t0, a0
  sw t0, 28(sp)
  lui t1, %hi(k)
  addi t1, t1, %lo(k)
  lw t0, 28(sp)
  sw t0, 0(t1)
  lui t1, %hi(p)
  addi t1, t1, %lo(p)
  lw t0, 0(t1)
  sw t0, 32(sp)
  lui t1, %hi(k)
  addi t1, t1, %lo(k)
  lw t0, 0(t1)
  sw t0, 36(sp)
  lw t1, 32(sp)
  lw t0, 36(sp)
  sub t0, t1, t0
  sw t0, 40(sp)
  li t1, 1
  lw t0, 40(sp)
  slt t0, t1, t0
  sw t0, 48(sp)
  lw t1, 48(sp)
  beqz t1, .L2
  j .L1
.L1:
  li t1, 0
  slli t0, t1, 2
  sw t0, 52(sp)
  lui t1, %hi(.str.0)
  addi t1, t1, %lo(.str.0)
  lw t0, 52(sp)
  add t0, t1, t0
  sw t0, 56(sp)
  lw t1, 56(sp)
  mv a0, t1
  call print
  j .L2
.L2:
  lui t1, %hi(p)
  addi t1, t1, %lo(p)
  lw t0, 0(t1)
  sw t0, 60(sp)
  lui t1, %hi(k)
  addi t1, t1, %lo(k)
  lw t0, 0(t1)
  sw t0, 64(sp)
  lw t1, 60(sp)
  lw t0, 64(sp)
  sub t0, t1, t0
  sw t0, 68(sp)
  lui t1, %hi(i)
  addi t1, t1, %lo(i)
  lw t0, 68(sp)
  sw t0, 0(t1)
  j .L3
.L3:
  lui t1, %hi(p)
  addi t1, t1, %lo(p)
  lw t0, 0(t1)
  sw t0, 72(sp)
  lui t1, %hi(k)
  addi t1, t1, %lo(k)
  lw t0, 0(t1)
  sw t0, 76(sp)
  lw t1, 72(sp)
  lw t0, 76(sp)
  add t0, t1, t0
  sw t0, 80(sp)
  lui t1, %hi(i)
  addi t1, t1, %lo(i)
  lw t0, 0(t1)
  sw t0, 84(sp)
  lw t1, 80(sp)
  lw t0, 84(sp)
  slt t0, t1, t0
  sw t0, 88(sp)
  lw t1, 88(sp)
  xori t0, t1, 1
  sw t0, 92(sp)
  lw t1, 92(sp)
  beqz t1, .L15
  j .L4
.L4:
  lui t1, %hi(i)
  addi t1, t1, %lo(i)
  lw t0, 0(t1)
  sw t0, 96(sp)
  lw t1, 96(sp)
  li t0, 1
  slt t0, t1, t0
  sw t0, 100(sp)
  lw t1, 100(sp)
  xori t0, t1, 1
  sw t0, 104(sp)
  lw t1, 104(sp)
  beqz t1, .L7
  j .L5
.L5:
  lui t1, %hi(i)
  addi t1, t1, %lo(i)
  lw t0, 0(t1)
  sw t0, 108(sp)
  lui t1, %hi(n)
  addi t1, t1, %lo(n)
  lw t0, 0(t1)
  sw t0, 112(sp)
  lw t1, 112(sp)
  lw t0, 108(sp)
  slt t0, t1, t0
  sw t0, 116(sp)
  lw t1, 116(sp)
  xori t0, t1, 1
  sw t0, 120(sp)
  lw t1, 120(sp)
  beqz t1, .L7
  j .L6
.L6:
  lw t1, 16(sp)
  li t0, 1
  sb t0, 0(t1)
  j .L8
.L7:
  lw t1, 16(sp)
  li t0, 0
  sb t0, 0(t1)
  j .L8
.L8:
  lw t1, 16(sp)
  lb t0, 0(t1)
  sb t0, 124(sp)
  lb t1, 124(sp)
  mv t0, t1
  sw t0, 128(sp)
  lw t1, 128(sp)
  beqz t1, .L13
  j .L9
.L9:
  lui t1, %hi(i)
  addi t1, t1, %lo(i)
  lw t0, 0(t1)
  sw t0, 132(sp)
  lui t1, %hi(p)
  addi t1, t1, %lo(p)
  lw t0, 0(t1)
  sw t0, 136(sp)
  lw t1, 132(sp)
  lw t0, 136(sp)
  sub t0, t1, t0
  sw t0, 140(sp)
  lw t1, 140(sp)
  seqz t0, t1
  sw t0, 144(sp)
  lw t1, 144(sp)
  beqz t1, .L11
  j .L10
.L10:
  li t1, 0
  slli t0, t1, 2
  sw t0, 148(sp)
  lui t1, %hi(.str.1)
  addi t1, t1, %lo(.str.1)
  lw t0, 148(sp)
  add t0, t1, t0
  sw t0, 152(sp)
  lw t1, 152(sp)
  mv a0, t1
  call print
  lui t1, %hi(i)
  addi t1, t1, %lo(i)
  lw t0, 0(t1)
  sw t0, 156(sp)
  lw t1, 156(sp)
  mv a0, t1
  call toString
  mv t0, a0
  sw t0, 160(sp)
  lw t1, 160(sp)
  mv a0, t1
  call print
  li t1, 0
  slli t0, t1, 2
  sw t0, 164(sp)
  lui t1, %hi(.str.2)
  addi t1, t1, %lo(.str.2)
  lw t0, 164(sp)
  add t0, t1, t0
  sw t0, 168(sp)
  lw t1, 168(sp)
  mv a0, t1
  call print
  j .L12
.L11:
  lui t1, %hi(i)
  addi t1, t1, %lo(i)
  lw t0, 0(t1)
  sw t0, 172(sp)
  lw t1, 172(sp)
  mv a0, t1
  call printInt
  li t1, 0
  slli t0, t1, 2
  sw t0, 176(sp)
  lui t1, %hi(.str.3)
  addi t1, t1, %lo(.str.3)
  lw t0, 176(sp)
  add t0, t1, t0
  sw t0, 180(sp)
  lw t1, 180(sp)
  mv a0, t1
  call print
  j .L12
.L12:
  j .L13
.L13:
  j .L14
.L14:
  lui t1, %hi(i)
  addi t1, t1, %lo(i)
  lw t0, 0(t1)
  sw t0, 184(sp)
  lw t1, 184(sp)
  li t0, 1
  add t0, t1, t0
  sw t0, 188(sp)
  lui t1, %hi(i)
  addi t1, t1, %lo(i)
  lw t0, 188(sp)
  sw t0, 0(t1)
  j .L3
.L15:
  lui t1, %hi(p)
  addi t1, t1, %lo(p)
  lw t0, 0(t1)
  sw t0, 192(sp)
  lui t1, %hi(k)
  addi t1, t1, %lo(k)
  lw t0, 0(t1)
  sw t0, 196(sp)
  lw t1, 192(sp)
  lw t0, 196(sp)
  add t0, t1, t0
  sw t0, 200(sp)
  lui t1, %hi(n)
  addi t1, t1, %lo(n)
  lw t0, 0(t1)
  sw t0, 204(sp)
  lw t1, 200(sp)
  lw t0, 204(sp)
  slt t0, t1, t0
  sw t0, 212(sp)
  lw t1, 212(sp)
  beqz t1, .L17
  j .L16
.L16:
  li t1, 0
  slli t0, t1, 2
  sw t0, 216(sp)
  lui t1, %hi(.str.4)
  addi t1, t1, %lo(.str.4)
  lw t0, 216(sp)
  add t0, t1, t0
  sw t0, 220(sp)
  lw t1, 220(sp)
  mv a0, t1
  call print
  j .L17
.L17:
  lw t1, 12(sp)
  li t0, 0
  sw t0, 0(t1)
  j .L18
.L18:
  lw t1, 12(sp)
  lw t0, 0(t1)
  sw t0, 224(sp)
  lw t1, 224(sp)
  mv a0, t1
  lw ra, 0(sp)
  li t0, 228
  add sp, sp, t0
  ret
