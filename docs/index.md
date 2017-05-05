<b>Why Bless is better than Cure Wounds in combat.</b>

Any action you take in combat will change the amount of HP you have at the end of combat. The actions that result in the greatest amount of HP at the end of combat are the best actions to take if your goal is have the greatest possible amount of HP at the end of combat.

<i>(This means that when circumstances outside of combat dictate your goals, having the greatest possible amount of HP at the end of combat is not necessarily your goal.)</i>

&#09; We assume that your goal as a player is to have the greatest possible amount of HP at the end of combat.

In combat, enemy actions change the amount of HP you have at the end of combat. Most enemy actions involve spells and spell or weapon attacks that reduce your HP. Your goal is to prevent your enemies from successfully using these actions. We assume that the way enemies are prevented from taking actions that reduce your HP is for you to reduce their HP to 0 by taking your own actions.
The attack action, bless and cure wounds each have different rules, but accomplish the same thing in combat: changing the amount of HP you have at the end of combat. Our goal is to discover which actions are the best for you to take in combat, that is, which actions prevent enemies from taking actions that reduce your HP.

<i>(These are the axioms that support the following logic:)</i>

It is possible to take the attack action every round your HP is greater than 0. Taking the attack action reduces enemy HP. When an enemy's HP is reduced to 0, it cannot take actions that reduce your HP. Attacking in combat helps accomplish your goal.
Casting cure wounds does not reduce enemy HP. Casting cure wounds in combat does not help to accomplish your goal, except when an ally is at 0 HP, meaning they cannot take actions.

When you or your allies are affected by bless and you take the attack action, bless is helping to accomplish your goal. It probabilistically increases the damage you or your allies cause by taking the attack action. In fact, the average increase in damage caused by you or your allies when affected by bless is greater than the damage caused by an taking the attack action.
Therefore, you want to cast bless in combat, take the attack action until your enemy's HP is reduced to 0.

<b>The conclusion is incorrect because the axioms do not accurately model the game.</b>

Beginning 5e, my groups' clerics often deliberated whether to use bless or guiding bolt, and we didn't consider using cure wounds because it didn't make enemies die faster. Guiding bolt deals 4d6 (UP TO 24), which seems like a lot. But being the clever players we were, we thought about how great a percentage increase in damage from bless we could inflict over multiple rounds. We decided to go with bless, and count all the damage we did with attacks that only hit because of bless. Without getting too specific, the sum was usually over 20, so we decided that it was a good spell.

A little over a year later, the point was brought up that "doing 20 extra damage isn't the same thing as preventing 20 damage to your party." The full argument being: "DPR optimization is only a little slice of the equation. The higher your party's DPR is, the faster the fight ends. Less rounds means less incoming damage, which means more longevity. The goal is to survive the most fights, not end fights quickly. So you look at a combat choice and compare where you think the party's resources will be after the fight. Everything else is irrelevant. Resources are: health, spell slots, abilities left, weapon charges, etc."

Consider the following: A level 2 party is fighting a lizardfolk. Do you attack the lizardfolk on your first turn, or do you bless?

Then, consider how that changes versus 2 lizardfolk, 3 lizardfolk, 4, 5, etc. How much damage is the attack preventing? How much damage is the bless preventing? Keep your guesses in mind.

<b>How does bless actually prevent damage?</b>

Bless prevents damage when attacks that only hit because of bless cause an enemy to die before they would have hit with an attack action, or multiple attack actions over multiple turns, had been alive. 

<b>How do we count the damage prevented by bless?</b>

The most accurate way to count the damage prevented by bless goes about like this: you bless, and then the first time it hits you record the state of the game. Then you run x simulations from that point as though it hit and y simulation as though it didn't and you say "this particular blessed hit saved z damage on average."

I don't think that's even worth getting into, but I think there is a way you can say "bless caused x damage" in a way that is agreeable and apparant to everyone.

We use a script that simulates combat between a party of characters and an encounter of monsters. The characters and monsters roll initiative, then they take turns attacking. Characters all target the same monster with their attacks until the monster is dead. The monsters attack characters randomly and won't attack a character that is incapacitated. #The party of characters includes a cleric, who will cast its highest level cure wounds on# The script simulates 3000 encounters and prints the total HP of the party when all the monsters are dead.

In most of our simulations, the party of characters comprises a Cleric, a Fighter, a Paladin and a Sorcerer. We run the script once with the party's default HP, AC and attacks, then we run the script once with the Cleric casting Bless before combat, targeting the Cleric, the Fighter and the Paladin. Additionally, it is not possible for the Cleric to stop concentrating on Bless (We'll explain this later). We expect that the average total HP of the party will be higher when the Cleric casts Bless.

<b>Simulation and Results</b>

In these two simulations, the party is level 2 and the encounters are 2 (easy), 3 (hard) or 4 (deadly) Lizardfolk. The Cleric has 16 Strength, 14 Constitution, 18 AC (Chainmail, Shield), and attacks with +5 to hit for 1d8+3 damage. The Fighter has 16 Strength, 16 Constitution, 18 AC (Chainmail, Shield), and attacks with +5 to hit for 1d8+5 damage (Dueling). The Paladin has 16 Strength, 16 Constitution, 18 AC (Chainmail, Shield), and attacks with +5 to hit for 1d8+5 damage (Dueling). The Sorcerer has 16 Charisma, 12 Constitution, 15 AC (Dragonic Resilience, Dexterity), and attacks with +5 to hit for 1d10 damage. The Lizardfolk each have 15 AC and two attacks with +4 to hit for 1d6+2. In other words they're bog standard.

These are the results of fighting without Bless and fighting with Bless.

Without bless: 2 {:hp 55.09667} 3 {:hp 35.487335} 4 {:hp 14.121333}
With bless: 2 {:hp 58.326} 3 {:hp 41.906666} 4 {:hp 20.404667}

The average of the total HP when the Cleric casts bless is 58.32 versus 2 Lizardfolk, 41.90 versus 3 Lizardfolk, and 20.40 versus 4 Lizardfolk. The average of the total HP when the Cleric does not cast bless is 55.10 versus 2 Lizardfolk, 35.49 versus 3 Lizardfolk, and 14.12 versus 4 Lizardfolk.

We feel justified in saying that in these combat examples that bless prevented 3.22 during the average encounter with 2 Lizardfolk , 6.41 during the average encounter with 3 Lizardfolk, and 6.28 damage during the average encounter with 4 Lizardfolk. In this encounter, the Cleric has cast Bless without using an action and maintains concentration regardless of the damage the Cleric takes while the Lizardfolk attack party members randomly. In this case, the best possible case for bless, Bless comes out slightly behind Cure Wounds. 
First round bless. Average across 3000 sims: 98.9hp:
```
simulation# 6
encounter# 0
round# 0
:sorcerer misses :orog0
:orog0 hits :paladin for 7
:orog0 hits :paladin for 6
:orog1 misses :paladin
:orog1 crits :cleric for 8
:orog2 hits :cleric for 11
:orog2 misses :fighter
:fighter misses :orog0
:fighter misses :orog0
:fighter hits :orog0 for 7
:paladin hits :orog0 for 9
:paladin misses :orog0
:cleric blesses :fighter :paladin :cleric
round# 1
:sorcerer misses :orog0
:orog0 misses :cleric
:orog0 misses :paladin
:orog1 misses :cleric
:orog1 misses :cleric
:orog2 misses :fighter
:orog2 hits :cleric for 15
:fighter misses :orog0
:fighter misses :orog0
:fighter misses :orog0
:paladin misses :orog0
:paladin hits :orog0 for 9 #blessed
:cleric hits :orog0 for 7
:cleric misses :orog0
round# 2
:sorcerer misses :orog0
:orog0 hits :cleric for 14
:orog0 misses :paladin
:orog1 misses :sorcerer
:orog1 hits :paladin for 16
:orog2 misses :fighter
:orog2 misses :fighter
:fighter hits :orog0 for 8
:fighter misses :orog0
:fighter misses :orog0
:paladin hits :orog0 for 13
:paladin hits :orog1 for 8
round# 3
:sorcerer hits :orog1 for 9
:orog1 hits :fighter for 14
:orog1 hits :fighter for 13
:orog2 hits :sorcerer for 12
:orog2 hits :paladin for 10
:fighter misses :orog1
:fighter misses :orog1
:fighter misses :orog1
:paladin hits :orog1 for 9 #blessed
:paladin misses :orog1
round# 4
:sorcerer hits :orog1 for 18
:orog2 misses :sorcerer
:orog2 misses :fighter
:fighter hits :orog2 for 7
:fighter misses :orog2
:fighter hits :orog2 for 8
:paladin hits :orog2 for 7 #blessed
:paladin hits :orog2 for 14
round# 5
:sorcerer hits :orog2 for 8
:fighter hits :orog0 for 9
:fighter misses :orog0
:fighter hits :orog0 for 7
:paladin hits :orog0 for 12
:paladin hits :orog0 for 13
:cleric has no remaining spell slots to heal with
remaining hp: 48

simulation# 4
encounter# 0
round# 0
:cleric blesses :fighter :paladin :cleric
:paladin misses :orog0
:paladin hits :orog0 for 8 #blessed
:orog0 hits :cleric for 15
:orog0 hits :paladin for 10
the sorcerer uses shield to block :orog1
:orog1 misses :sorcerer
:orog1 misses :fighter
:orog2 crits :paladin for 18
:orog2 hits :fighter for 10
:sorcerer hits :orog0 for 5
:fighter hits :orog0 for 7
:fighter hits :orog0 for 11
:fighter misses :orog0
round# 1
:cleric misses :orog0
:cleric hits :orog0 for 11
:paladin hits :orog1 for 13
:paladin misses :orog1
:orog1 misses :fighter
:orog1 hits :cleric for 15
:orog2 hits :sorcerer for 16
:orog2 misses :cleric
:sorcerer hits :orog1 for 15
:fighter misses :orog1
:fighter hits :orog1 for 12
:fighter hits :orog2 for 7 #blessed
round# 2
:cleric misses :orog2
:cleric hits :orog2 for 8
:paladin misses :orog2
:paladin misses :orog2
:orog2 misses :cleric
:orog2 crits :sorcerer for 11
:sorcerer hits :orog2 for 14
:fighter hits :orog2 for 10
:fighter hits :orog0 for 9
:fighter misses :orog0
:cleric has no remaining spell slots to heal with
remaining hp: 69

simulation# 7
encounter# 0
round# 0
:orog0 hits :fighter for 14
:orog0 hits :paladin for 8
:orog1 hits :paladin for 11
:orog1 misses :cleric
:orog2 misses :paladin
:orog2 misses :paladin
:paladin hits :orog0 for 10
:paladin misses :orog0
:sorcerer misses :orog0
:fighter hits :orog0 for 10
:fighter hits :orog0 for 7
:fighter hits :orog0 for 9
:cleric blesses :fighter :paladin :cleric
round# 1
:orog1 hits :paladin for 5
:orog1 hits :paladin for 15
:orog2 hits :cleric for 13
:orog2 hits :fighter for 12
:paladin misses :orog1
:paladin hits :orog1 for 12 #blessed
:sorcerer misses :orog1
:fighter hits :orog1 for 8 #blessed
:fighter hits :orog1 for 12 #blessed
:fighter hits :orog1 for 8
:cleric hits :orog1 for 8
:cleric hits :orog1 for 8
round# 2
the sorcerer uses shield to block :orog2
:orog2 misses :sorcerer
:orog2 hits :sorcerer for 13
:paladin hits :orog2 for 14
:paladin hits :orog2 for 14
:sorcerer misses :orog2
:fighter misses :orog2
:fighter hits :orog2 for 12 #blessed
:fighter misses :orog0
:cleric hits :orog0 for 7 #blessed
:cleric misses :orog0
:cleric has no remaining spell slots to heal with
remaining hp: 73

simulation# 1
encounter# 0
round# 0
:orog0 misses :paladin
:orog0 misses :cleric
the sorcerer uses shield to block :orog1
:orog1 misses :sorcerer
:orog1 misses :paladin
:orog2 hits :sorcerer for 16
:orog2 crits :fighter for 16
:cleric blesses :fighter :paladin :cleric
:paladin hits :orog0 for 11
:paladin hits :orog0 for 13
:fighter hits :orog0 for 9
:fighter misses :orog0
:fighter hits :orog0 for 7
:sorcerer misses :orog0
round# 1
:orog0 misses :sorcerer
:orog0 crits :sorcerer for 20
:orog1 misses :sorcerer
:orog1 misses :fighter
:orog2 misses :sorcerer
:orog2 misses :cleric
:cleric hits :orog0 for 7
:cleric misses :orog0
:paladin crits :orog0 for 19
:paladin hits :orog1 for 10
:fighter hits :orog1 for 10
:fighter misses :orog1
:fighter misses :orog1
:sorcerer misses :orog1
round# 2
:orog1 hits :fighter for 7
:orog1 hits :paladin for 15
:orog2 misses :paladin
:orog2 hits :cleric for 11
:cleric hits :orog1 for 5
:cleric hits :orog1 for 7
:paladin misses :orog1
:paladin hits :orog1 for 10
:fighter hits :orog1 for 11
:fighter misses :orog2
:fighter crits :orog2 for 9
:sorcerer hits :orog2 for 18
round# 3
:orog2 misses :fighter
:orog2 misses :cleric
:cleric hits :orog2 for 11 #blessed
:cleric misses :orog2
:paladin crits :orog2 for 11
:paladin misses :orog0
:fighter hits :orog0 for 10
:fighter hits :orog0 for 10
:fighter hits :orog0 for 7
:sorcerer hits :orog0 for 6
:cleric has no remaining spell slots to heal with
remaining hp: 79

simulation# 10
encounter# 0
round# 0
:paladin misses :orog0
:paladin misses :orog0
:fighter misses :orog0
:fighter misses :orog0
:fighter hits :orog0 for 9
:sorcerer hits :orog0 for 10
:orog0 hits :fighter for 11
:orog0 hits :fighter for 14
:orog1 misses :paladin
:orog1 hits :cleric for 12
:orog2 misses :paladin
:orog2 misses :fighter
:cleric blesses :fighter :paladin :cleric
round# 1
:paladin hits :orog0 for 13
:paladin hits :orog0 for 9 #blessed
:fighter hits :orog1 for 8
:fighter misses :orog1
:fighter misses :orog1
:sorcerer misses :orog1
:orog1 hits :paladin for 10
:orog1 hits :sorcerer for 8
:orog2 misses :paladin
:orog2 misses :cleric
:cleric hits :orog1 for 10
:cleric hits :orog1 for 8
round# 2
:paladin hits :orog1 for 14
:paladin misses :orog1
:fighter hits :orog1 for 8
:fighter hits :orog1 for 11
:fighter misses :orog2
:sorcerer misses :orog2
:orog2 misses :paladin
the sorcerer uses shield to block :orog2
:orog2 misses :sorcerer
:cleric hits :orog2 for 4
:cleric misses :orog2
round# 3
:paladin misses :orog2
:paladin hits :orog2 for 12
:fighter misses :orog2
:fighter misses :orog2
:fighter misses :orog2
:sorcerer hits :orog2 for 9
:orog2 misses :fighter
:orog2 hits :fighter for 14
:cleric misses :orog2
:cleric hits :orog2 for 9
round# 4
:paladin misses :orog2
:paladin misses :orog2
:fighter misses :orog2
:fighter hits :orog2 for 9
:fighter misses :orog0
:sorcerer crits :orog0 for 19
:cleric hits :orog0 for 7 #blessed
:cleric hits :orog0 for 10
:cleric has no remaining spell slots to heal with
remaining hp: 95

simulation# 2
encounter# 0
round# 0
:cleric blesses :fighter :paladin :cleric
:paladin misses :orog0
:paladin hits :orog0 for 7
:sorcerer misses :orog0
:orog0 hits :cleric for 8
:orog0 misses :fighter
:orog1 misses :paladin
:orog1 hits :cleric for 15
:orog2 misses :sorcerer
:orog2 misses :cleric
:fighter misses :orog0
:fighter hits :orog0 for 9 #blessed
:fighter hits :orog0 for 7
round# 1
:cleric hits :orog0 for 6
:cleric hits :orog0 for 9
:paladin hits :orog0 for 14 #blessed
:paladin hits :orog1 for 14
:sorcerer misses :orog1
the sorcerer uses shield to block :orog1
:orog1 misses :sorcerer
:orog1 hits :cleric for 13
:orog2 hits :sorcerer for 8
:orog2 crits :paladin for 18
:fighter hits :orog1 for 10
:fighter hits :orog1 for 7
:fighter hits :orog1 for 7
round# 2
:cleric misses :orog2
:cleric misses :orog2
:paladin hits :orog2 for 7
:paladin hits :orog2 for 12 #blessed
:sorcerer hits :orog2 for 14
the sorcerer uses shield to block :orog2
:orog2 misses :sorcerer
:orog2 misses :fighter
:fighter hits :orog2 for 11
:fighter hits :orog0 for 11
:fighter hits :orog0 for 9 #blessed
:cleric has no remaining spell slots to heal with
remaining hp: 102

simulation# 0
encounter# 0
round# 0
:paladin misses :orog0
:paladin misses :orog0
:fighter hits :orog0 for 10
:fighter hits :orog0 for 8
:fighter crits :orog0 for 11
:cleric blesses :fighter :paladin :cleric
:orog0 hits :fighter for 6
:orog0 misses :cleric
:orog1 hits :sorcerer for 7
:orog1 hits :fighter for 5
:orog2 misses :cleric
:orog2 misses :cleric
:sorcerer hits :orog0 for 9
round# 1
:paladin hits :orog0 for 13 #blessed
:paladin misses :orog1
:fighter hits :orog1 for 7
:fighter misses :orog1
:fighter misses :orog1
:cleric hits :orog1 for 8
:cleric misses :orog1
:orog1 hits :cleric for 10
:orog1 misses :fighter
:orog2 misses :paladin
:orog2 misses :cleric
:sorcerer misses :orog1
round# 2
:paladin misses :orog1
:paladin misses :orog1
:fighter hits :orog1 for 10
:fighter crits :orog1 for 13
:fighter misses :orog1
:cleric misses :orog1
:cleric hits :orog1 for 11
:orog2 misses :fighter
:orog2 hits :fighter for 8
:sorcerer misses :orog2
round# 3
:paladin misses :orog2
:paladin misses :orog2
:fighter hits :orog2 for 10
:fighter misses :orog2
:fighter hits :orog2 for 7
:cleric hits :orog2 for 5
:cleric hits :orog2 for 8 #blessed
:orog2 hits :paladin for 8
:orog2 hits :paladin for 14
:sorcerer hits :orog2 for 8
round# 4
:paladin misses :orog2
:paladin misses :orog2
:fighter hits :orog2 for 7 #blessed
:fighter misses :orog0
:fighter hits :orog0 for 8
:cleric hits :orog0 for 7
:cleric misses :orog0
:sorcerer hits :orog0 for 14
:cleric has no remaining spell slots to heal with
remaining hp: 106

simulation# 5
encounter# 0
round# 0
:sorcerer crits :orog0 for 17
:paladin misses :orog0
:paladin hits :orog0 for 10
:orog0 misses :sorcerer
:orog0 hits :paladin for 16
:orog1 hits :cleric for 15
:orog1 misses :fighter
:orog2 hits :paladin for 12
:orog2 hits :paladin for 6
:fighter hits :orog0 for 10
:fighter misses :orog1
:fighter misses :orog1
:cleric blesses :fighter :paladin :cleric
round# 1
:sorcerer misses :orog1
:paladin crits :orog1 for 14
:paladin crits :orog1 for 14
the sorcerer uses shield to block :orog1
:orog1 misses :sorcerer
:orog1 misses :cleric
:orog2 hits :paladin for 8
:orog2 misses :fighter
:fighter misses :orog1
:fighter misses :orog1
:fighter hits :orog1 for 9
:cleric misses :orog1
:cleric crits :orog1 for 12
round# 2
:sorcerer hits :orog2 for 18
:paladin hits :orog2 for 13
:paladin hits :orog2 for 14
:fighter hits :orog0 for 12 #blessed
:fighter misses :orog0
:fighter hits :orog0 for 10 #blessed
:cleric hits :orog0 for 4
:cleric hits :orog0 for 9 #blessed
:cleric has no remaining spell slots to heal with
remaining hp: 107

simulation# 9
encounter# 0
round# 0
:sorcerer crits :orog0 for 28
:paladin misses :orog0
:paladin hits :orog0 for 11
:fighter misses :orog0
:fighter misses :orog0
:fighter hits :orog0 for 8
:orog0 misses :cleric
:orog0 misses :cleric
:orog1 misses :paladin
:orog1 hits :paladin for 6
the sorcerer uses shield to block :orog2
:orog2 misses :sorcerer
:orog2 hits :paladin for 6
:cleric blesses :fighter :paladin :cleric
round# 1
:sorcerer hits :orog0 for 11
:paladin hits :orog1 for 10
:paladin misses :orog1
:fighter misses :orog1
:fighter hits :orog1 for 12
:fighter misses :orog1
the sorcerer uses shield to block :orog1
:orog1 misses :sorcerer
:orog1 hits :sorcerer for 15
:orog2 misses :fighter
:orog2 hits :cleric for 8
:cleric hits :orog1 for 9
:cleric misses :orog1
round# 2
:sorcerer hits :orog1 for 13
:paladin hits :orog2 for 14
:paladin misses :orog2
:fighter hits :orog2 for 7
:fighter misses :orog2
:fighter hits :orog2 for 7
:orog2 hits :cleric for 11
:orog2 misses :paladin
:cleric hits :orog2 for 7
:cleric misses :orog2
round# 3
:sorcerer misses :orog2
:paladin hits :orog2 for 9
:paladin hits :orog0 for 10 #blessed
:fighter hits :orog0 for 8 #blessed
:fighter hits :orog0 for 10
:fighter hits :orog0 for 10
:cleric hits :orog0 for 6 #blessed
:cleric hits :orog0 for 7
:cleric has no remaining spell slots to heal with
remaining hp: 118

simulation# 3
encounter# 0
round# 0
:fighter hits :orog0 for 7
:fighter hits :orog0 for 8
:fighter misses :orog0
:paladin misses :orog0
:paladin hits :orog0 for 13
:cleric blesses :fighter :paladin :cleric
:orog0 misses :fighter
:orog0 hits :fighter for 9
:orog1 misses :sorcerer
:orog1 misses :paladin
:orog2 misses :paladin
:orog2 hits :cleric for 12
:sorcerer misses :orog0
round# 1
:fighter misses :orog0
:fighter misses :orog0
:fighter hits :orog0 for 7 #blessed
:paladin misses :orog0
:paladin misses :orog0
:cleric hits :orog0 for 11
:cleric hits :orog1 for 8
:orog1 misses :paladin
:orog1 misses :cleric
:orog2 crits :cleric for 14
:orog2 misses :fighter
:sorcerer misses :orog1
round# 2
:fighter hits :orog1 for 8 #blessed
:fighter hits :orog1 for 8
:fighter hits :orog1 for 7
:paladin hits :orog1 for 12
:paladin crits :orog2 for 15
:cleric misses :orog2
:cleric misses :orog2
:orog2 misses :fighter
:orog2 misses :cleric
:sorcerer misses :orog2
round# 3
:fighter misses :orog2
:fighter hits :orog2 for 11
:fighter hits :orog2 for 8 #blessed
:paladin misses :orog2
:paladin misses :orog2
:cleric hits :orog2 for 6
:cleric hits :orog2 for 9
:sorcerer misses :orog0
:cleric has no remaining spell slots to heal with
remaining hp: 129

simulation# 8
encounter# 0
round# 0
:sorcerer hits :orog0 for 5
:paladin hits :orog0 for 11
:paladin hits :orog0 for 13
:orog0 misses :cleric
:orog0 misses :fighter
:orog1 misses :cleric
:orog1 misses :paladin
:orog2 hits :fighter for 8
:orog2 misses :fighter
:fighter hits :orog0 for 9
:fighter misses :orog0
:fighter misses :orog0
:cleric blesses :fighter :paladin :cleric
round# 1
:sorcerer hits :orog0 for 10
:paladin hits :orog1 for 9
:paladin hits :orog1 for 11
the sorcerer uses shield to block :orog1
:orog1 misses :sorcerer
:orog1 misses :cleric
:orog2 hits :cleric for 12
:orog2 misses :fighter
:fighter hits :orog1 for 8 #blessed
:fighter misses :orog1
:fighter misses :orog1
:cleric hits :orog1 for 11
:cleric misses :orog2
round# 2
:sorcerer misses :orog2
:paladin hits :orog2 for 14
:paladin hits :orog2 for 14
:orog2 misses :paladin
:orog2 hits :paladin for 9
:fighter hits :orog2 for 9
:fighter crits :orog2 for 11
:fighter hits :orog0 for 9
:cleric hits :orog0 for 11
:cleric misses :orog0
:cleric has no remaining spell slots to heal with
remaining hp: 135
```

No bless at all. Average across 3000 sims: 105.4 hp.
```
simulation# 10
encounter# 0
round# 0
:fighter misses :orog0
:fighter misses :orog0
:fighter misses :orog0
:cleric hits :orog0 for 10
:cleric misses :orog0
:sorcerer hits :orog0 for 4
:orog0 hits :cleric for 16
:orog0 hits :fighter for 6
:orog1 hits :cleric for 9
:orog1 hits :cleric for 15
:orog2 hits :fighter for 13
:orog2 hits :sorcerer for 5
:paladin hits :orog0 for 9
:paladin hits :orog0 for 13
round# 1
:fighter misses :orog0
:fighter hits :orog0 for 8
:fighter misses :orog0
:sorcerer misses :orog0
:orog0 misses :sorcerer
:orog0 misses :paladin
:orog1 hits :paladin for 14
:orog1 hits :paladin for 8
:orog2 misses :sorcerer
:orog2 crits :sorcerer for 17
:paladin misses :orog0
:paladin misses :orog0
round# 2
:fighter misses :orog0
:fighter misses :orog0
:fighter misses :orog0
:sorcerer misses :orog0
:orog0 misses :fighter
:orog0 misses :sorcerer
:orog1 hits :fighter for 12
:orog1 hits :paladin for 6
:orog2 hits :fighter for 15
:orog2 misses :paladin
:paladin misses :orog0
:paladin hits :orog0 for 9
round# 3
:sorcerer misses :orog1
the sorcerer uses shield to block :orog1
:orog1 misses :sorcerer
:orog1 hits :paladin for 9
:orog2 misses :paladin
:orog2 hits :paladin for 14
round# 4
:sorcerer misses :orog1
:orog1 hits :sorcerer for 15
the sorcerer uses shield to block :orog1
:orog1 misses :sorcerer
:orog2 misses :sorcerer
:orog2 misses :sorcerer
round# 5
:sorcerer hits :orog1 for 12
:orog1 hits :sorcerer for 12
:orog1 hits :paladin for 5
:orog2 hits :paladin for 9
:orog2 misses :paladin
:cleric casts cure wound and heals :fighter to 11
:cleric has no remaining spell slots to heal with
remaining hp: 11

simulation# 8
encounter# 0
round# 0
:sorcerer misses :orog0
:fighter misses :orog0
:fighter misses :orog0
:fighter misses :orog0
:paladin hits :orog0 for 8
:paladin misses :orog0
:orog0 misses :paladin
:orog0 hits :fighter for 10
:orog1 misses :sorcerer
:orog1 hits :paladin for 14
:orog2 hits :cleric for 13
:orog2 misses :sorcerer
:cleric misses :orog0
:cleric misses :orog0
round# 1
:sorcerer misses :orog0
:fighter misses :orog0
:fighter misses :orog0
:fighter hits :orog0 for 7
:paladin hits :orog0 for 8
:paladin misses :orog0
:orog0 hits :cleric for 15
:orog0 crits :fighter for 16
:orog1 hits :fighter for 16
:orog1 hits :sorcerer for 16
:orog2 misses :sorcerer
:orog2 misses :paladin
:cleric misses :orog0
:cleric hits :orog0 for 11
round# 2
:sorcerer hits :orog0 for 10
:fighter hits :orog0 for 12
:fighter hits :orog1 for 9
:fighter misses :orog1
:paladin misses :orog1
:paladin hits :orog1 for 7
:orog1 misses :sorcerer
:orog1 misses :cleric
the sorcerer uses shield to block :orog2
:orog2 misses :sorcerer
:orog2 hits :sorcerer for 11
:cleric misses :orog1
:cleric hits :orog1 for 8
round# 3
:sorcerer misses :orog1
:fighter hits :orog1 for 8
:fighter misses :orog1
:fighter misses :orog1
:paladin hits :orog1 for 8
:paladin misses :orog1
:orog1 misses :sorcerer
:orog1 misses :cleric
:orog2 misses :cleric
:orog2 misses :cleric
:cleric misses :orog1
:cleric hits :orog1 for 5
round# 4
:sorcerer misses :orog2
:fighter misses :orog2
:fighter misses :orog2
:fighter hits :orog2 for 10
:paladin hits :orog2 for 7
:paladin hits :orog2 for 9
:orog2 misses :cleric
:orog2 hits :sorcerer for 7
:cleric misses :orog2
:cleric misses :orog2
round# 5
:sorcerer misses :orog2
:fighter misses :orog2
:fighter misses :orog2
:fighter misses :orog2
:paladin hits :orog2 for 8
:paladin misses :orog2
:orog2 misses :fighter
:orog2 hits :sorcerer for 16
:cleric casts cure wound and heals :sorcerer to 10
round# 6
:sorcerer hits :orog2 for 4
:fighter misses :orog2
:fighter hits :orog2 for 10
:fighter hits :orog0 for 7
:paladin misses :orog0
:paladin misses :orog0
:cleric hits :orog0 for 11
:cleric misses :orog0
:cleric has no remaining spell slots to heal with
remaining hp: 52

simulation# 7
encounter# 0
round# 0
:fighter misses :orog0
:fighter misses :orog0
:fighter misses :orog0
:cleric hits :orog0 for 6
:cleric misses :orog0
:paladin misses :orog0
:paladin hits :orog0 for 11
:sorcerer misses :orog0
:orog0 hits :cleric for 8
:orog0 crits :paladin for 16
:orog1 crits :fighter for 27
:orog1 hits :sorcerer for 7
:orog2 misses :sorcerer
:orog2 hits :fighter for 14
round# 1
:fighter hits :orog0 for 9
:fighter hits :orog0 for 9
:fighter hits :orog0 for 7
:cleric misses :orog0
:cleric hits :orog0 for 7
:paladin misses :orog1
:paladin hits :orog1 for 14
:sorcerer hits :orog1 for 10
:orog1 misses :fighter
:orog1 hits :fighter for 16
:orog2 misses :sorcerer
:orog2 misses :sorcerer
round# 2
:cleric casts cure wound and heals :fighter to 15
:paladin misses :orog1
:paladin misses :orog1
:sorcerer misses :orog1
:orog1 misses :fighter
:orog1 misses :paladin
:orog2 hits :fighter for 10
:orog2 hits :paladin for 5
round# 3
:fighter misses :orog1
:fighter misses :orog1
:fighter hits :orog1 for 10
:cleric misses :orog1
:cleric hits :orog1 for 9
:paladin misses :orog1
:paladin hits :orog1 for 10
:sorcerer hits :orog2 for 3
:orog2 hits :fighter for 9
:orog2 crits :cleric for 16
round# 4
:cleric misses :orog2
:cleric hits :orog2 for 11
:paladin misses :orog2
:paladin misses :orog2
:sorcerer hits :orog2 for 3
:orog2 misses :cleric
:orog2 misses :paladin
round# 5
:cleric hits :orog2 for 6
:cleric misses :orog2
:paladin misses :orog2
:paladin hits :orog2 for 10
:sorcerer hits :orog2 for 11
:cleric has no remaining spell slots to heal with
remaining hp: 68

simulation# 9
encounter# 0
round# 0
:fighter crits :orog0 for 15
:fighter misses :orog0
:fighter misses :orog0
:orog0 hits :cleric for 7
:orog0 hits :cleric for 12
:orog1 misses :fighter
:orog1 misses :paladin
:orog2 crits :cleric for 18
:orog2 hits :cleric for 15
:paladin hits :orog0 for 7
:paladin hits :orog0 for 12
:sorcerer misses :orog0
round# 1
:fighter hits :orog0 for 11
:fighter misses :orog0
:fighter hits :orog0 for 8
:orog1 misses :sorcerer
:orog1 hits :fighter for 16
the sorcerer uses shield to block :orog2
:orog2 misses :sorcerer
:orog2 misses :paladin
:paladin hits :orog1 for 7
:paladin misses :orog1
:sorcerer hits :orog1 for 14
round# 2
:fighter hits :orog1 for 11
:fighter misses :orog1
:fighter hits :orog1 for 10
:orog1 misses :paladin
:orog1 misses :sorcerer
:orog2 misses :paladin
:orog2 crits :paladin for 14
:paladin misses :orog1
:paladin hits :orog1 for 11
:sorcerer crits :orog2 for 18
round# 3
:fighter crits :orog2 for 10
:fighter misses :orog2
:fighter misses :orog2
:orog2 misses :sorcerer
:orog2 hits :fighter for 9
:paladin hits :orog2 for 12
:paladin hits :orog0 for 13
:sorcerer hits :orog0 for 13
:cleric casts cure wound and heals :cleric to 9
:cleric has no remaining spell slots to heal with
remaining hp: 96

simulation# 0
encounter# 0
round# 0
:cleric hits :orog0 for 7
:cleric misses :orog0
:sorcerer crits :orog0 for 15
:orog0 hits :cleric for 10
:orog0 hits :fighter for 11
:orog1 misses :cleric
:orog1 hits :cleric for 12
:orog2 hits :cleric for 7
:orog2 hits :fighter for 12
:paladin hits :orog0 for 7
:paladin misses :orog0
:fighter hits :orog0 for 9
:fighter hits :orog1 for 11
:fighter misses :orog1
round# 1
:cleric hits :orog1 for 5
:cleric misses :orog1
:sorcerer misses :orog1
:orog1 misses :fighter
:orog1 misses :fighter
:orog2 hits :sorcerer for 11
:orog2 misses :paladin
:paladin hits :orog1 for 9
:paladin hits :orog1 for 9
:fighter hits :orog1 for 12
:fighter hits :orog2 for 12
:fighter hits :orog2 for 9
round# 2
:cleric misses :orog2
:cleric hits :orog2 for 4
:sorcerer misses :orog2
:orog2 hits :fighter for 10
:orog2 hits :paladin for 5
:paladin misses :orog2
:paladin hits :orog2 for 12
:fighter misses :orog2
:fighter misses :orog2
:fighter misses :orog2
round# 3
:cleric hits :orog2 for 9
:cleric misses :orog0
:sorcerer hits :orog0 for 13
:paladin hits :orog0 for 11
:paladin hits :orog0 for 11
:fighter hits :orog0 for 11
:fighter misses :orog0
:fighter misses :orog0
:cleric casts cure wound and heals :fighter to 24
:cleric has no remaining spell slots to heal with
remaining hp: 99

simulation# 4
encounter# 0
round# 0
:fighter misses :orog0
:fighter hits :orog0 for 12
:fighter hits :orog0 for 7
:orog0 misses :fighter
:orog0 misses :cleric
:orog1 hits :sorcerer for 12
:orog1 misses :fighter
:orog2 crits :fighter for 15
:orog2 hits :fighter for 9
:paladin crits :orog0 for 13
:paladin misses :orog0
:sorcerer hits :orog0 for 11
:cleric hits :orog1 for 7
:cleric hits :orog1 for 8
round# 1
:fighter hits :orog1 for 12
:fighter misses :orog1
:fighter misses :orog1
:orog1 misses :sorcerer
:orog1 hits :fighter for 14
:orog2 misses :cleric
:orog2 hits :cleric for 15
:paladin misses :orog1
:paladin hits :orog1 for 13
:sorcerer hits :orog1 for 16
:cleric hits :orog2 for 6
:cleric misses :orog2
round# 2
:fighter hits :orog2 for 7
:fighter crits :orog2 for 16
:fighter crits :orog2 for 11
:paladin misses :orog0
:paladin hits :orog0 for 9
:sorcerer hits :orog0 for 15
:cleric misses :orog0
:cleric hits :orog0 for 11
:cleric casts cure wound and heals :fighter to 17
:cleric has no remaining spell slots to heal with
remaining hp: 110

simulation# 2
encounter# 0
round# 0
:fighter misses :orog0
:fighter misses :orog0
:fighter misses :orog0
:sorcerer misses :orog0
:cleric misses :orog0
:cleric misses :orog0
:paladin misses :orog0
:paladin hits :orog0 for 10
:orog0 misses :cleric
:orog0 misses :paladin
:orog1 misses :paladin
:orog1 misses :fighter
:orog2 crits :sorcerer for 20
:orog2 misses :cleric
round# 1
:fighter hits :orog0 for 9
:fighter misses :orog0
:fighter misses :orog0
:sorcerer hits :orog0 for 6
:cleric hits :orog0 for 9
:cleric hits :orog0 for 11
:paladin crits :orog1 for 19
:paladin misses :orog1
:orog1 misses :sorcerer
:orog1 hits :cleric for 15
:orog2 hits :sorcerer for 6
:orog2 hits :sorcerer for 9
round# 2
:fighter misses :orog1
:fighter misses :orog1
:fighter misses :orog1
:sorcerer misses :orog1
:cleric misses :orog1
:cleric hits :orog1 for 11
:paladin misses :orog1
:paladin hits :orog1 for 9
:orog2 misses :paladin
:orog2 hits :paladin for 16
round# 3
:fighter hits :orog2 for 9
:fighter hits :orog2 for 8
:fighter misses :orog2
:sorcerer misses :orog2
:cleric misses :orog2
:cleric hits :orog2 for 4
:paladin crits :orog2 for 20
:paladin misses :orog2
:orog2 misses :fighter
:orog2 misses :sorcerer
round# 4
:fighter hits :orog2 for 11
:fighter misses :orog0
:fighter misses :orog0
:sorcerer misses :orog0
:cleric misses :orog0
:cleric misses :orog0
:paladin misses :orog0
:paladin hits :orog0 for 8
:cleric casts cure wound and heals :sorcerer to 17
:cleric has no remaining spell slots to heal with
remaining hp: 112

simulation# 6
encounter# 0
round# 0
:paladin misses :orog0
:paladin hits :orog0 for 9
:fighter misses :orog0
:fighter hits :orog0 for 10
:fighter misses :orog0
:cleric hits :orog0 for 7
:cleric misses :orog0
:orog0 hits :cleric for 10
:orog0 misses :fighter
:orog1 misses :fighter
:orog1 hits :sorcerer for 9
:orog2 misses :paladin
:orog2 misses :fighter
:sorcerer misses :orog0
round# 1
:paladin hits :orog0 for 9
:paladin misses :orog0
:fighter hits :orog0 for 11
:fighter crits :orog1 for 12
:fighter hits :orog1 for 10
:cleric hits :orog1 for 11
:cleric misses :orog1
:orog1 misses :sorcerer
:orog1 hits :cleric for 14
:orog2 misses :cleric
:orog2 hits :paladin for 11
:sorcerer misses :orog1
round# 2
:paladin misses :orog1
:paladin hits :orog1 for 7
:fighter misses :orog1
:fighter hits :orog1 for 12
:fighter misses :orog2
:cleric hits :orog2 for 4
:cleric hits :orog2 for 10
the sorcerer uses shield to block :orog2
:orog2 misses :sorcerer
:orog2 hits :sorcerer for 16
:sorcerer misses :orog2
round# 3
:paladin hits :orog2 for 12
:paladin misses :orog2
:fighter misses :orog2
:fighter misses :orog2
:fighter misses :orog2
:cleric misses :orog2
:cleric hits :orog2 for 11
:sorcerer hits :orog0 for 7
:cleric casts cure wound and heals :sorcerer to 25
:cleric has no remaining spell slots to heal with
remaining hp: 116

simulation# 5
encounter# 0
round# 0
:orog0 misses :paladin
:orog0 misses :paladin
:orog1 misses :sorcerer
the sorcerer uses shield to block :orog1
:orog1 misses :sorcerer
:orog2 hits :paladin for 6
:orog2 misses :paladin
:cleric misses :orog0
:cleric misses :orog0
:paladin hits :orog0 for 10
:paladin misses :orog0
:sorcerer misses :orog0
:fighter misses :orog0
:fighter crits :orog0 for 13
:fighter hits :orog0 for 7
round# 1
:orog0 misses :sorcerer
:orog0 misses :paladin
:orog1 misses :paladin
:orog1 hits :cleric for 11
:orog2 hits :paladin for 7
:orog2 misses :fighter
:cleric misses :orog0
:cleric crits :orog0 for 14
:paladin hits :orog0 for 8
:paladin hits :orog1 for 8
:sorcerer misses :orog1
:fighter misses :orog1
:fighter misses :orog1
:fighter hits :orog1 for 7
round# 2
:orog1 misses :paladin
:orog1 hits :cleric for 15
:orog2 hits :cleric for 10
:orog2 misses :paladin
:cleric hits :orog1 for 11
:cleric crits :orog1 for 13
:paladin hits :orog2 for 9
:paladin misses :orog2
:sorcerer misses :orog2
:fighter hits :orog2 for 12
:fighter misses :orog2
:fighter crits :orog2 for 11
round# 3
the sorcerer uses shield to block :orog2
:orog2 misses :sorcerer
:orog2 misses :sorcerer
:cleric misses :orog2
:cleric misses :orog2
:paladin misses :orog2
:paladin misses :orog2
:sorcerer hits :orog2 for 8
:fighter misses :orog2
:fighter hits :orog2 for 11
:fighter hits :orog0 for 8
:cleric casts cure wound and heals :cleric to 16
:cleric has no remaining spell slots to heal with
remaining hp: 129

simulation# 1
encounter# 0
round# 0
:cleric misses :orog0
:cleric misses :orog0
:fighter misses :orog0
:fighter crits :orog0 for 15
:fighter misses :orog0
:orog0 misses :paladin
:orog0 misses :cleric
:orog1 misses :cleric
:orog1 hits :sorcerer for 9
:orog2 misses :cleric
:orog2 misses :paladin
:paladin misses :orog0
:paladin misses :orog0
:sorcerer misses :orog0
round# 1
:cleric hits :orog0 for 11
:cleric misses :orog0
:fighter crits :orog0 for 17
:fighter misses :orog1
:fighter misses :orog1
:orog1 misses :paladin
:orog1 misses :fighter
:orog2 crits :sorcerer for 11
:orog2 misses :fighter
:paladin hits :orog1 for 10
:paladin misses :orog1
:sorcerer hits :orog1 for 11
round# 2
:cleric hits :orog1 for 11
:cleric hits :orog1 for 8
:fighter hits :orog1 for 7
:fighter hits :orog2 for 10
:fighter misses :orog2
:orog2 misses :paladin
:orog2 hits :cleric for 15
:paladin misses :orog2
:paladin hits :orog2 for 12
:sorcerer crits :orog2 for 23
:cleric casts cure wound and heals :sorcerer to 26
:cleric has no remaining spell slots to heal with
remaining hp: 137

simulation# 3
encounter# 0
round# 0
:fighter crits :orog0 for 13
:fighter hits :orog0 for 11
:fighter misses :orog0
:sorcerer misses :orog0
:cleric misses :orog0
:cleric hits :orog0 for 9
the sorcerer uses shield to block :orog0
:orog0 misses :sorcerer
:orog0 hits :fighter for 6
:orog1 misses :cleric
:orog1 misses :sorcerer
:orog2 misses :sorcerer
:orog2 misses :paladin
:paladin misses :orog0
:paladin misses :orog0
round# 1
:fighter crits :orog0 for 13
:fighter misses :orog0
:fighter hits :orog0 for 9
:sorcerer misses :orog1
:cleric misses :orog1
:cleric misses :orog1
:orog1 misses :sorcerer
:orog1 misses :paladin
:orog2 crits :fighter for 16
:orog2 misses :fighter
:paladin hits :orog1 for 14
:paladin hits :orog1 for 10
round# 2
:fighter hits :orog1 for 12
:fighter hits :orog1 for 12
:fighter hits :orog2 for 8
:sorcerer hits :orog2 for 14
:cleric misses :orog2
:cleric misses :orog2
:orog2 misses :fighter
:orog2 misses :fighter
:paladin misses :orog2
:paladin misses :orog2
round# 3
:fighter misses :orog2
:fighter hits :orog2 for 11
:fighter hits :orog2 for 8
:sorcerer misses :orog0
:cleric misses :orog0
:cleric misses :orog0
:paladin hits :orog0 for 10
:paladin misses :orog0
:cleric casts cure wound and heals :fighter to 34
:cleric has no remaining spell slots to heal with
remaining hp: 154
```

Blessing before combat. Average across 3000 sims: 107.3
```
simulation# 1
encounter# 0
:cleric blesses :fighter :paladin :cleric
round# 0
:paladin misses :orog0
:paladin hits :orog0 for 14 #blessed
:orog0 misses :cleric
:orog0 hits :cleric for 5
:orog1 misses :sorcerer
:orog1 hits :paladin for 16
:orog2 crits :cleric for 15
:orog2 hits :fighter for 8
:fighter hits :orog0 for 7
:fighter misses :orog0
:fighter hits :orog0 for 8
:sorcerer misses :orog0
:cleric hits :orog0 for 7
:cleric misses :orog0
round# 1
:paladin hits :orog0 for 11 #blessed
:paladin hits :orog0 for 12 #blessed
the sorcerer uses shield to block :orog1
:orog1 misses :sorcerer
:orog1 hits :cleric for 9
:orog2 hits :sorcerer for 11
:orog2 hits :cleric for 6
:fighter hits :orog1 for 12
:fighter hits :orog1 for 10
:fighter hits :orog1 for 10 #blessed
:sorcerer hits :orog1 for 6
:cleric hits :orog1 for 7
:cleric misses :orog2
round# 2
:paladin hits :orog2 for 9
:paladin hits :orog2 for 7
:orog2 hits :paladin for 5
the sorcerer uses shield to block :orog2
:orog2 misses :sorcerer
:fighter hits :orog2 for 10
:fighter misses :orog2
:fighter misses :orog2
:sorcerer hits :orog2 for 8
:cleric misses :orog2
:cleric misses :orog2
round# 3
:paladin hits :orog2 for 8
:paladin hits :orog2 for 13 #blessed
:fighter hits :orog0 for 10
:fighter hits :orog0 for 9 #blessed
:fighter misses :orog0
:sorcerer hits :orog0 for 9
:cleric misses :orog0
:cleric misses :orog0
:cleric has no remaining spell slots to heal with
remaining hp: 89

simulation# 2
encounter# 0
:cleric blesses :fighter :paladin :cleric
round# 0
:sorcerer misses :orog0
:orog0 hits :cleric for 8
:orog0 misses :cleric
:orog1 misses :sorcerer
:orog1 misses :fighter
:orog2 hits :paladin for 11
the sorcerer uses shield to block :orog2
:orog2 misses :sorcerer
:paladin misses :orog0
:paladin misses :orog0
:fighter hits :orog0 for 8 #blessed
:fighter hits :orog0 for 11 #blessed
:fighter hits :orog0 for 9 #blessed
:cleric misses :orog0
:cleric hits :orog0 for 9 #blessed
round# 1
:sorcerer misses :orog0
:orog0 misses :paladin
:orog0 misses :paladin
:orog1 misses :paladin
the sorcerer uses shield to block :orog1
:orog1 misses :sorcerer
:orog2 hits :paladin for 5
:orog2 misses :sorcerer
:paladin hits :orog0 for 10
:paladin misses :orog1
:fighter misses :orog1
:fighter hits :orog1 for 12 #blessed
:fighter hits :orog1 for 8
:cleric hits :orog1 for 4
:cleric hits :orog1 for 8
round# 2
:sorcerer hits :orog1 for 7
:orog1 misses :fighter
:orog1 hits :fighter for 16
:orog2 hits :fighter for 12
:orog2 crits :cleric for 16
:paladin misses :orog1
:paladin hits :orog1 for 9
:fighter hits :orog2 for 9
:fighter hits :orog2 for 12 #blessed
:fighter misses :orog2
:cleric hits :orog2 for 7 #blessed
:cleric hits :orog2 for 10 #blessed
round# 3
:sorcerer hits :orog2 for 12
:paladin hits :orog0 for 7
:paladin misses :orog0
:fighter hits :orog0 for 10
:fighter misses :orog0
:fighter hits :orog0 for 9
:cleric hits :orog0 for 10
:cleric misses :orog0
:cleric has no remaining spell slots to heal with
remaining hp: 96

simulation# 7
encounter# 0
:cleric blesses :fighter :paladin :cleric
round# 0
:orog0 misses :paladin
:orog0 misses :cleric
:orog1 misses :cleric
:orog1 hits :sorcerer for 10
:orog2 hits :cleric for 12
the sorcerer uses shield to block :orog2
:orog2 misses :sorcerer
:fighter hits :orog0 for 8
:fighter hits :orog0 for 12 #blessed
:fighter hits :orog0 for 9 #blessed
:sorcerer hits :orog0 for 14
:paladin hits :orog0 for 8
:paladin misses :orog1
:cleric hits :orog1 for 10
:cleric hits :orog1 for 4
round# 1
:orog1 hits :paladin for 15
:orog1 misses :fighter
:orog2 crits :cleric for 24
:orog2 hits :cleric for 14
:fighter hits :orog1 for 12 #blessed
:fighter hits :orog1 for 11
:fighter hits :orog1 for 8 #blessed
:sorcerer hits :orog2 for 14
:paladin misses :orog2
:paladin hits :orog2 for 12 #blessed
round# 2
:orog2 misses :sorcerer
the sorcerer uses shield to block :orog2
:orog2 misses :sorcerer
:fighter misses :orog2
:fighter misses :orog2
:fighter misses :orog2
:sorcerer hits :orog2 for 7
:paladin misses :orog2
:paladin hits :orog2 for 11
:cleric has no remaining spell slots to heal with
remaining hp: 101

simulation# 10
encounter# 0
:cleric blesses :fighter :paladin :cleric
round# 0
:paladin misses :orog0
:paladin hits :orog0 for 14
:cleric hits :orog0 for 11
:cleric hits :orog0 for 8
:orog0 misses :paladin
:orog0 hits :cleric for 6
:orog1 hits :paladin for 14
:orog1 hits :paladin for 5
:orog2 misses :paladin
:orog2 hits :fighter for 16
:sorcerer misses :orog0
:fighter hits :orog0 for 9
:fighter misses :orog0
:fighter misses :orog0
round# 1
:paladin hits :orog0 for 9
:paladin misses :orog1
:cleric hits :orog1 for 7 #blessed
:cleric hits :orog1 for 4
:orog1 hits :fighter for 9
:orog1 misses :fighter
the sorcerer uses shield to block :orog2
:orog2 misses :sorcerer
:orog2 misses :sorcerer
:sorcerer hits :orog1 for 12
:fighter hits :orog1 for 10 #blessed
:fighter misses :orog1
:fighter hits :orog1 for 8
round# 2
:paladin hits :orog1 for 11
:paladin crits :orog2 for 8
:cleric hits :orog2 for 9 #blessed
:cleric misses :orog2
:orog2 misses :fighter
:orog2 hits :cleric for 7
:sorcerer hits :orog2 for 11
:fighter hits :orog2 for 12
:fighter hits :orog2 for 8 #blessed
:fighter hits :orog0 for 9
:cleric has no remaining spell slots to heal with
remaining hp: 107

simulation# 5
encounter# 0
:cleric blesses :fighter :paladin :cleric
round# 0
:fighter hits :orog0 for 9
:fighter hits :orog0 for 10 #blessed
:fighter hits :orog0 for 7
:paladin hits :orog0 for 7
:paladin hits :orog0 for 8 #blessed
:cleric hits :orog1 for 9
:cleric hits :orog1 for 9 #blessed
:orog1 misses :sorcerer
:orog1 hits :cleric for 7
:orog2 hits :paladin for 9
:orog2 hits :cleric for 13
:sorcerer misses :orog1
round# 1
:fighter misses :orog1
:fighter misses :orog1
:fighter misses :orog1
:paladin hits :orog1 for 11
:paladin misses :orog1
:cleric crits :orog1 for 13
:cleric misses :orog1
:orog1 hits :fighter for 12
:orog1 hits :cleric for 14
:orog2 misses :fighter
:orog2 misses :sorcerer
:sorcerer crits :orog1 for 20
round# 2
:fighter hits :orog2 for 7
:fighter hits :orog2 for 8 #blessed
:fighter hits :orog2 for 10 #blessed
:paladin hits :orog2 for 9
:paladin hits :orog2 for 11
:cleric misses :orog0
:cleric hits :orog0 for 5 #blessed
:sorcerer misses :orog0
:cleric has no remaining spell slots to heal with
remaining hp: 109

simulation# 6
encounter# 0
:cleric blesses :fighter :paladin :cleric
round# 0
:fighter hits :orog0 for 11
:fighter hits :orog0 for 10
:fighter misses :orog0
:orog0 misses :cleric
:orog0 misses :sorcerer
:orog1 hits :fighter for 14
:orog1 hits :cleric for 14
:orog2 misses :paladin
:orog2 hits :cleric for 9
:sorcerer hits :orog0 for 13
:paladin misses :orog0
:paladin misses :orog0
:cleric misses :orog0
:cleric misses :orog0
round# 1
:fighter hits :orog0 for 11
:fighter hits :orog1 for 10
:fighter misses :orog1
the sorcerer uses shield to block :orog1
:orog1 misses :sorcerer
:orog1 hits :sorcerer for 8
:orog2 hits :paladin for 10
:orog2 misses :paladin
:sorcerer misses :orog1
:paladin hits :orog1 for 14
:paladin hits :orog1 for 8
:cleric misses :orog1
:cleric misses :orog1
round# 2
:fighter hits :orog1 for 12
:fighter misses :orog2
:fighter hits :orog2 for 8
the sorcerer uses shield to block :orog2
:orog2 misses :sorcerer
:orog2 misses :cleric
:sorcerer hits :orog2 for 8
:paladin misses :orog2
:paladin misses :orog2
:cleric hits :orog2 for 5
:cleric hits :orog2 for 6 #blessed
round# 3
:fighter hits :orog2 for 8 #blessed
:fighter hits :orog2 for 11 #blessed
:fighter misses :orog0
:sorcerer hits :orog0 for 17
:paladin misses :orog0
:paladin hits :orog0 for 11 #blessed
:cleric hits :orog0 for 11 #blessed
:cleric hits :orog0 for 4
:cleric has no remaining spell slots to heal with
remaining hp: 109

simulation# 8
encounter# 0
:cleric blesses :fighter :paladin :cleric
round# 0
:cleric crits :orog0 for 19
:cleric crits :orog0 for 17
:sorcerer hits :orog0 for 17
:fighter misses :orog1
:fighter hits :orog1 for 10 #blessed
:fighter hits :orog1 for 7
:orog1 misses :fighter
:orog1 misses :cleric
:orog2 hits :sorcerer for 13
:orog2 hits :sorcerer for 15
:paladin hits :orog1 for 14
:paladin hits :orog1 for 14
round# 1
:cleric misses :orog2
:cleric hits :orog2 for 9 #blessed
:sorcerer misses :orog2
:fighter hits :orog2 for 9
:fighter hits :orog2 for 7 #blessed
:fighter hits :orog2 for 10
:orog2 misses :paladin
:orog2 hits :cleric for 15
:paladin misses :orog2
:paladin misses :orog2
round# 2
:cleric misses :orog2
:cleric hits :orog2 for 6
:sorcerer hits :orog2 for 15
:fighter misses :orog0
:fighter hits :orog0 for 12
:fighter misses :orog0
:paladin misses :orog0
:paladin misses :orog0
:cleric has no remaining spell slots to heal with
remaining hp: 121

simulation# 9
encounter# 0
:cleric blesses :fighter :paladin :cleric
round# 0
:paladin crits :orog0 for 17
:paladin misses :orog0
:sorcerer crits :orog0 for 31
:fighter crits :orog1 for 16
:fighter hits :orog1 for 7
:fighter misses :orog1
:orog1 hits :fighter for 12
:orog1 misses :paladin
:orog2 misses :paladin
:orog2 misses :cleric
:cleric hits :orog1 for 6 #blessed
:cleric hits :orog1 for 6 #blessed
round# 1
:paladin hits :orog1 for 7 #blessed
:paladin misses :orog2
:sorcerer hits :orog2 for 4
:fighter hits :orog2 for 11 #blessed
:fighter misses :orog2
:fighter hits :orog2 for 9 #blessed
:orog2 hits :cleric for 8
:orog2 hits :cleric for 12
:cleric hits :orog2 for 5
:cleric misses :orog2
round# 2
:paladin misses :orog2
:paladin hits :orog2 for 10
:sorcerer misses :orog0
:fighter hits :orog0 for 7 #blessed
:fighter hits :orog0 for 9
:fighter misses :orog0
:cleric hits :orog0 for 5 #blessed
:cleric hits :orog0 for 6
:cleric has no remaining spell slots to heal with
remaining hp: 132

simulation# 0
encounter# 0
:cleric blesses :fighter :paladin :cleric
round# 0
:orog0 misses :cleric
:orog0 hits :fighter for 16
:orog1 misses :fighter
:orog1 hits :sorcerer for 6
:orog2 misses :paladin
:orog2 misses :sorcerer
:paladin hits :orog0 for 7 #blessed
:paladin hits :orog0 for 12 #blessed
:fighter hits :orog0 for 7
:fighter hits :orog0 for 8
:fighter hits :orog0 for 7
:cleric hits :orog1 for 11
:cleric hits :orog1 for 9
:sorcerer crits :orog1 for 19
round# 1
:orog1 hits :paladin for 5
:orog1 misses :paladin
:orog2 misses :sorcerer
:orog2 misses :paladin
:paladin misses :orog1
:paladin hits :orog1 for 12
:fighter hits :orog2 for 11
:fighter hits :orog2 for 8
:fighter hits :orog2 for 7
:cleric misses :orog2
:cleric misses :orog2
:sorcerer misses :orog2
round# 2
:orog2 misses :sorcerer
the sorcerer uses shield to block :orog2
:orog2 misses :sorcerer
:paladin misses :orog2
:paladin hits :orog2 for 14
:fighter hits :orog2 for 12
:fighter hits :orog0 for 7
:fighter hits :orog0 for 9
:cleric hits :orog0 for 8 #blessed
:cleric misses :orog0
:sorcerer misses :orog0
:cleric has no remaining spell slots to heal with
remaining hp: 137

simulation# 4
encounter# 0
:cleric blesses :fighter :paladin :cleric
round# 0
the sorcerer uses shield to block :orog0
:orog0 misses :sorcerer
:orog0 hits :fighter for 15
:orog1 hits :fighter for 5
:orog1 misses :cleric
:orog2 misses :cleric
:orog2 misses :fighter
:cleric hits :orog0 for 11
:cleric hits :orog0 for 9
:paladin misses :orog0
:paladin hits :orog0 for 11
:fighter hits :orog0 for 11
:fighter hits :orog0 for 9 #blessed
:fighter misses :orog1
:sorcerer misses :orog1
round# 1
:orog1 misses :fighter
:orog1 misses :fighter
:orog2 misses :fighter
:orog2 misses :cleric
:cleric misses :orog1
:cleric misses :orog1
:paladin hits :orog1 for 12
:paladin misses :orog1
:fighter misses :orog1
:fighter hits :orog1 for 10 #blessed
:fighter hits :orog1 for 9
:sorcerer hits :orog1 for 16
round# 2
:orog2 hits :fighter for 6
:orog2 misses :paladin
:cleric hits :orog2 for 9
:cleric crits :orog2 for 15
:paladin hits :orog2 for 10
:paladin misses :orog2
:fighter misses :orog2
:fighter hits :orog2 for 10
:fighter misses :orog2
:sorcerer hits :orog2 for 4
:cleric has no remaining spell slots to heal with
remaining hp: 138

simulation# 3
encounter# 0
:cleric blesses :fighter :paladin :cleric
round# 0
:orog0 misses :cleric
:orog0 misses :paladin
the sorcerer uses shield to block :orog1
:orog1 misses :sorcerer
:orog1 misses :fighter
:orog2 misses :paladin
:orog2 hits :cleric for 11
:sorcerer misses :orog0
:cleric hits :orog0 for 11
:cleric misses :orog0
:fighter hits :orog0 for 7 #blessed
:fighter hits :orog0 for 7 #blessed
:fighter hits :orog0 for 7 #blessed
:paladin misses :orog0
:paladin hits :orog0 for 7
round# 1
:orog1 misses :paladin
:orog1 hits :paladin for 10
the sorcerer uses shield to block :orog2
:orog2 misses :sorcerer
:orog2 misses :paladin
:sorcerer crits :orog1 for 21
:cleric misses :orog1
:cleric crits :orog1 for 18
:fighter hits :orog1 for 11
:fighter hits :orog2 for 12 #blessed
:fighter hits :orog2 for 9
:paladin misses :orog2
:paladin hits :orog2 for 9 #blessed
round# 2
:orog2 misses :sorcerer
:orog2 misses :cleric
:sorcerer hits :orog2 for 14
:cleric misses :orog0
:cleric misses :orog0
:fighter misses :orog0
:fighter hits :orog0 for 8 #blessed
:fighter hits :orog0 for 8
:paladin hits :orog0 for 12
:paladin hits :orog0 for 9
:cleric has no remaining spell slots to heal with
remaining hp: 143
```

<b>Why Cure Wounds is the best Cleric spell</b>

The average result of 1d8 is 4.5. When a Cleric casts Cure Wounds, the Cleric adds their Wisdom modifier to the result of a d8. The expected value of casting Cure Wounds is 4.5 + WIS. If your level 1 Cleric has 16 Wisdom, you probably expect to heal 7 or 8 HP when you cast Cure Wounds. How much damage did Bless prevent?

<b>Using spell slots during a simulated adventuring day</b>

Using spells like haste and burning hands conditionally.

<b>Which spells are good?</b>

Burning Hands, Fireball, Prayer of Healing etc.

<b>Which class features are the best?</b>

Which class features preserve the most resources over a simulated adventuring day? Remarks on defensive vs dueling style. Using rages. Action surge and Second wind.

<b>The number 1 class?</b>

Which class's presence preserves the most resources over a simulated adventuring day?
