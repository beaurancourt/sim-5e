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
