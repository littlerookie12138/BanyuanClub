package club.banyuan;

public class Main {
    public static void main(String[] args) {
        Fighter fighter1 = new Fighter("双倍攻击技能", "眩晕技能", "太极拳", "九阳神功", "乾坤大挪移");
        //太极拳造成58点伤害，并减少下次受到的伤害15% 九阳神功回100点血 乾坤大挪移使用对方的一个技能
        Fighter fighter2 = new Fighter("双倍攻击技能", "眩晕技能", "灭绝剑法", "紫玉周天功", "御风诀");
        //灭绝剑法造成100点伤害，并有20%几率暴击 紫玉周天功使下一回合攻击伤害*20% 御风诀下次收到伤害时有%60几率回避

        //张无忌的属性
        fighter1.setName("张无忌");
        fighter1.setWeapon("屠龙刀");
        fighter1.setWeapon("圣火令");

        //灭绝师太的属性
        fighter2.setName("灭绝师太");
        fighter2.setWeapon("倚天剑");

        int count = 0;

        //当两人的血量都大于0时持续战斗
        while (fighter1.getHP() > 0 && fighter2.getHP() > 0) {
            if (count % 2 == 0) {
                //张无忌出手
                if (fighter1.getVertigoOrNot()) {
                    //说明被眩晕了
                    fighter1.recover();
                    System.out.println(fighter1.getName() + "被眩晕了！");
                    count++;
                    System.out.println("---------------------------");
                    continue;
                } else {
                    fighter1.apply(fighter1, fighter2);
                    System.out.println(fighter2.getName() + "还剩" + fighter2.getHP());
                }
            } else {
                //师太出手
                if (fighter2.getVertigoOrNot()) {
                    //说明被眩晕了
                    fighter2.recover();
                    System.out.println(fighter2.getName() + "被眩晕了！");
                    count++;
                    System.out.println("---------------------------");
                    continue;
                } else {
                    fighter2.apply(fighter2, fighter1);
                    //张无忌的buff如果在的话要使用掉
                    if (fighter1.getBuff()) {
                        fighter1.setBuff(false);
                    }
                    System.out.println(fighter1.getName() + "还剩" + fighter1.getHP());
                }
            }

            System.out.println("---------------------------");
            count++;
        }
        if (fighter1.getHP() <= 0 && fighter2.getHP() > 0) {
            System.out.println("灭绝师太胜利!");
        } else if (fighter2.getHP() <= 0 && fighter1.getHP() > 0) {
            System.out.println("张无忌获得胜利!");
        }
    }
}
