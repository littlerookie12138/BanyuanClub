package club.banyuan;

import java.util.Random;

public class Fighter implements Weapon, Skills {
    private String name;
    private int HP = 500;
    private double Vertigo;//眩晕值 眩晕存在几率，根据武器判定，倚天剑有50的几率眩晕，屠龙刀只有30
    private String[] personalSkills = new String[10];
    private String[] weapon = new String[2];
    private boolean VertigoOrNot = false;
    private boolean buff = false;
    private boolean critBuff = false;
    private int countWeapon;

    public Fighter(String skill1, String skill2, String skill3, String skill4, String skill5) {
        personalSkills[0] = skill1;
        personalSkills[1] = skill2;
        personalSkills[2] = skill3;
        personalSkills[3] = skill4;
        personalSkills[4] = skill5;
    }

    public void hurt(int hurt) {
        this.HP -= hurt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getHP() {
        return HP;
    }

    public boolean getVertigoOrNot() {
        return VertigoOrNot;
    }

    public boolean getBuff() {
        return buff;
    }

    public void setBuff(boolean buff) {
        this.buff = buff;
    }

    public boolean getCritBuff() {
        return critBuff;
    }

    //太极拳造成58点伤害，并减少下次受到的伤害15% 九阳神功回150点血 乾坤大挪移使用对方的一个技能
    //灭绝剑法造成100点伤害，并有20%几率暴击 紫玉周天功使下一回合暴击buff*20% 御风诀下次收到伤害时有%60几率回避
    @Override
    public void apply(Fighter from, Fighter to) {
        Random random = new Random();
        int hurtHP = 0;
        int num = random.nextInt(4);

        if (from.personalSkills[num].equals(from.personalSkills[0])) {
            //使用了双倍攻击技能，判断张无忌有没有太极拳buff
            if (to.buff) {
                hurtHP = (int) (from.attack() * 2 * (1 - 0.15));
            } else {
                hurtHP = from.attack() * 2;
            }
            to.hurt(hurtHP);
        } else if (from.personalSkills[num].equals(from.personalSkills[1])) {
            //使用了眩晕技能
            //判断张无忌有没有太极拳buff
            if (to.buff) {
                hurtHP = (int) (from.attack() / 2 * (1 - 0.15));
            } else {
                hurtHP = from.attack() / 2;
            }
            to.Vertigo = random.nextDouble();
            if (from.getWeapon().equals("倚天剑")) {
                if (to.Vertigo <= 0.5) {
                    //眩晕成功
                    to.hurt(hurtHP);
                    to.VertigoOrNot = true;
                    System.out.println("眩晕成功!!!");
                } else {
                    //眩晕失败
                    to.hurt(hurtHP);
                    System.out.println("眩晕失败!");
                }
            } else if (from.getWeapon().equals("屠龙刀")) {
                if (to.Vertigo <= 0.3) {
                    //眩晕成功
                    to.hurt(hurtHP);
                    to.VertigoOrNot = true;
                    System.out.println("眩晕成功!!!");
                } else {
                    //眩晕失败
                    to.hurt(hurtHP);
                    System.out.println("眩晕失败!");
                }
            } else if (from.getWeapon().equals("圣火令")) {
                if (to.Vertigo <= 0.2) {
                    //眩晕成功
                    to.hurt(hurtHP);
                    to.VertigoOrNot = true;
                    System.out.println("眩晕成功!!!");
                } else {
                    //眩晕失败
                    to.hurt(hurtHP);
                    System.out.println("眩晕失败!");
                }
            }
        } else if (from.personalSkills[num].equals(from.personalSkills[2])) {
            if (from.personalSkills[num].equals("太极拳")) {
                hurtHP = 58;
                to.hurt(hurtHP);
                buff = true;
                System.out.println(from.getName() + "领悟太极真谛，下回合伤害减少15%");
            } else if (from.personalSkills[num].equals("灭绝剑法")) {
                //判断是否暴击
                double crit = random.nextDouble();
                //判断张无忌有没有太极拳buff
                if (to.buff) {
                    if (critBuff) {
                        crit += 0.2;
                        if (crit <= 0.5) {
                            hurtHP = (int) (100 * 2 * 0.85);
                            to.hurt(hurtHP);
                            System.out.println("灭绝师太灭情灭义，遁入志高境界，伤害暴击");
                        } else {
                            hurtHP = (int) (100 * 0.85);
                            to.hurt(hurtHP);
                        }
                    } else {
                        if (crit <= 0.2) {
                            hurtHP = (int) (100 * 2 * 0.85);
                            to.hurt(hurtHP);
                            System.out.println("灭绝师太灭情灭义，遁入志高境界，伤害暴击");
                        } else {
                            hurtHP = (int) (100 * 0.85);
                            to.hurt(hurtHP);
                        }
                    }
                } else {
                    if (crit <= 0.2) {
                        hurtHP = 100 * 2;
                        to.hurt(hurtHP);
                        System.out.println("灭绝师太灭情灭义，遁入志高境界，伤害暴击");
                    } else {
                        hurtHP = 100;
                        to.hurt(hurtHP);
                    }
                }
            }
        } else if (from.personalSkills[num].equals(from.personalSkills[3])) {
            if (from.personalSkills[num].equals("九阳神功")) {
                from.HP += 50;
                System.out.println(from.getName() + "装备了" + from.getWeapon() + "，使用了" + from.personalSkills[num] + "贯通人身大周天，回复气血");
                return;
            } else if (from.personalSkills[num].equals("紫玉周天功")) {
                //给自己加20%暴击buff
                critBuff = true;
                System.out.println(from.getName() + "装备了" + from.getWeapon() + "，使用了" + from.personalSkills[num] + "周身真气暴增，下次攻击暴击几率增加20%");
                return;
            }

        }

        System.out.println(from.getName() + "装备了" + from.getWeapon() + "，使用" + from.personalSkills[num] + "技能攻击" + to.getName() + ",造成了" + hurtHP + "点伤害");
    }

    public void setWeapon(String weapon) {
        this.weapon[countWeapon] = weapon;
        countWeapon++;
    }


    @Override
    public String getWeapon() {
        Random random = new Random();
        int numWeapon = randomInt(0, 2, random);
        while (weapon[numWeapon] == null) {
            numWeapon = randomInt(0, 2, random);
        }
        return weapon[numWeapon];
    }



    @Override
    public int attack() {
        Random random = new Random();

        if (getWeapon().equals("倚天剑")) {
            return 15;
        } else if (getWeapon().equals("屠龙刀")) {
            return randomInt(20, 31, random);
        } else if (getWeapon().equals("圣火令")) {
            return 50;
        }

        return 0;
    }

    public void recover() {
        if (VertigoOrNot) {
            //说明被眩晕了，跳过当前回合并回复眩晕状态
            VertigoOrNot = false;
            return;
        }
    }

    public static int randomInt(int from, int where, Random ran) {
        int n = where - from;
        if (n > 0) {
            return ran.nextInt(n) + from;
        } else {
            int r = 0;
            do {
                r = ran.nextInt();
            } while (r < from || r >= where);

            return r;
        }
    }
}
