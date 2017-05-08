### What Are We Fighting For?
As 5e DnD players, we find ourselves making choices that affect combat.

* A roleplay choice that forces or avoids combat.
* A build choice between damage or extra survivability.
* A combat choice between casting a damage spell, a buffing spell, a control spell, or a healing spell.

In order to answer these questions, we need *an objective function*. We need to define what the *goal* is.
We need a way to compare options against the objective function, so we can know what the right choice is.

Is the objective function maximizing DPR? [There](http://www.enworld.org/forum/showthread.php?362272-Highest-DPR-Build-Yet) are [plenty](http://www.giantitp.com/forums/showthread.php?522093-5e-Nova-or-DPR-optimization) of [threads](http://www.giantitp.com/forums/showthread.php?522096-Nova-or-DPR-optimization) that [attempt](http://www.giantitp.com/forums/showthread.php?474803-Good-sustained-dpr-builds) to [maximize](https://docs.google.com/spreadsheets/d/1Bwv7pfPC90BqZVPMu19T075-6cwkphdPJQe1d8gTVbQ/edit) dps.
There are more [guides](http://rpgbot.net/dnd5/characters/classes/) that offer optimization advice than I can count.

Yet, what are we *truly* trying to optimize for?

Here, I propose an optimization function: maximize the probability that your party lives through an adventuring day as defined by page 84 of the DMG.
One such adventuring day is 6 medium difficulty encounters for a party of 4 PCs at level 5.
The DMG suggests a short rest 1/3 and 2/3 the way through an adventuring day.

Why should that be the criteria? Why not optimize damage, or control, or utility, or some other measure?
At the end of the day, all of those are *generalized* by "living through the day".
At the end of the day, everything is reset, and you move on to your next day.

If you do great damage, then awesome! You got through your fights faster, and **your enemies have a harder time killing the party.**

If you have great crowd control, then awesome! You got through your fights with more control, and **your enemies have a harder time killing the party.**

If you have high utility, then awesome! You were able to cleverly bypass combat, or swing the battlefield to your advantage, and **your enemies have a harder time killing the party.**

If we have to give up utility for crowd control, how do we decide? If we have to give up crowd control for damage, how do we decide?

You decide by doing the thing that maximizes the chance that your party lives to fight another day!

### That sounds complicated in practice. Is there an easier way to think about it?

There sure is!

On an individual enounter bases, you want to make the choice that leaves you with the **most remaining resources**.

How does that work? If `choice A` leaves you with more remaining resources than `choice B` after one encounter,
then `choice A` is better than `choice B`. What are resources? All of the stuff that carries over: hp, spell slots, rages, action surges, rest dice, etc.

The easiest decisions to compare are the ones where all of the resource types stay constant except one!
This allows us to not worry about comparing resources to *each other* in terms of "living through the day" optimization and focus instead on which option maximizes a *particular* resource.

By maximizing the remaining resources after an encounter, we are choosing the option that puts us in the best position to **survive the rest of the day.**


### Okay. Maximize resources. But how do we measure?

If we try to create a closed form solution, we have a very, very difficult problem in front of us, and one that gets larger the more options we introduce.
If we try to theorycraft it, as is the current style, how do we resolve trading one resource for another? How much extra damage is worth extra survivability?
Taking note of [world of warcraft's solution](http://simulationcraft.org/), I propose that we simulate it!

Simulation tends to be prudent when the math we're working with is *already* heavily probabilistic in nature.
It won't get us the *exact* solution, but it'll get us *pretty darn close.*

Finally, simulation allows us to throw away our notions about what *should* work and purely observe evidence!

### That's all very abstract, math guy. Do you have an actual example?

Say that you're a level 5 party of: 1 duelist fighter, 1 vengence paladin, 1 light cleric, and 1 wild magic sorcerer.
You are the cleric, and you come across 3 [orogs](http://i.imgur.com/rHvLroX.png).
Your party is spent, or unwilling to use any more spell slots / abilities. You guys are, however, currently at full HP.
You have a level-1 spell slot, and a level-2 spell slot remaining. You have both bless and cure wounds prepared.
Say (additionally) that the enemies attack randomly, and that gaining a tactical battlefield advantage isn't possible.

Should you:
* Cast bless the first round of combat, as a level 1 spell, blessing the fighter, the paladin, and the sorcerer?
* Cast bless *before* combat, if you can?
* Attack the first round of combat, saving your slots to heal with?

I simulated 3000 runs of each choice and recorded the average total party hp remaining after each combat.
I posted the first 10 simulations of each choice further down the document, in case you want to see what the output looks like!

|Bless|No Bless|Pre-bless|
|-----|--------|---------|
|[98.8 hp](#first-round-bless-average-across-3000-sims-988-hp)|[99.2 hp](#no-bless-at-all-average-across-3000-sims-992-hp)|[104.6 hp](#blessing-before-combat-average-across-3000-sims-1046)|

If we can bless *before* combat starts, it's definitely worth it in this scenario. If we *can't*, and have to use our first action to bless, it's a toss-up.
It's worth further investigation to see how much of an affect intiative order matters.
For now, however, we see that blessing during combat isn't *enormously* better (and is worse, on average) than just attacking, and casting cure wounds afterwards.

### Why pick on Bless?

Bless vs Cure Wounds was chosen, specifically, for a few reasons:
* Bless has a *very* high reputation in the community (good candidate for being over-valued)
* Bless is a great conduit for comparing increased DPR vs survivability, and for comparing buffs vs the initiative.

The results give us good intuition about buff spells in general, and good intuition about how important the first round of combat is.

### What next?

I hope that simulation (not necessarily this tool) will usher in a new platform for balance debate,
and introduce a new way of optimizing builds (and reasoning about optimization).
Simulation is a powerful tool for DMs that want to get a feel for how strong to make a set-piece encounter,
or DMs that want to attempt balance changes.
Simulation is a powerful tool for Wizards of the Coast, so that they can better fine-tune their unearthed arcana (or future versions).

The project itself is open source, and open to both [pull requests](https://github.com/beaushinkle/sim-5e/pulls) and [test-scenario requests](https://github.com/beaushinkle/sim-5e/issues).
If there's a situation that you'd like tested (like duelist vs defensive fighting style for a sword + board fighter), [open an issue!](https://github.com/beaushinkle/sim-5e/issues)

### Data!

#### First round bless. Average across 3000 sims: 98.8 hp
<details>
<summary>45 hp</summary>
<pre><code>simulation# 0
encounter# 0
round# 0
:paladin misses :orog0
:paladin hits :orog0 for 7
:sorcerer hits :orog0 for 11
:cleric blesses :fighter :paladin :cleric
:orog0 misses :fighter
:orog0 misses :fighter
:orog1 hits :fighter for 9
:orog1 misses :paladin
:orog2 hits :sorcerer for 10
:orog2 hits :paladin for 12
:fighter hits :orog0 for 11 #blessed
:fighter crits :orog0 for 11
round# 1
:paladin misses :orog0
:paladin hits :orog0 for 14
:sorcerer misses :orog1
:cleric misses :orog1
:orog1 misses :paladin
:orog1 hits :sorcerer for 15
:orog2 crits :paladin for 12
:orog2 hits :paladin for 15
:fighter crits :orog1 for 16
:fighter misses :orog1
round# 2
:paladin misses :orog1
:paladin misses :orog1
:sorcerer misses :orog1
:cleric misses :orog1
:orog1 hits :fighter for 15
:orog1 misses :sorcerer
:orog2 hits :paladin for 8
:orog2 hits :fighter for 12
:fighter hits :orog1 for 14
:fighter hits :orog1 for 9
round# 3
:sorcerer misses :orog1
:cleric casts :spell-2 cure wound and heals :paladin from 0 to 18
:orog1 misses :paladin
:orog1 misses :fighter
:orog2 hits :sorcerer for 8
:orog2 hits :paladin for 8
:fighter misses :orog1
:fighter crits :orog1 for 12
round# 4
:paladin hits :orog2 for 14 #blessed
:paladin misses :orog2
:sorcerer hits :orog2 for 14
:cleric misses :orog2
:orog2 hits :paladin for 14
:orog2 misses :cleric
:fighter misses :orog2
:fighter hits :orog2 for 9
round# 5
:sorcerer misses :orog2
:cleric misses :orog2
:orog2 misses :cleric
:orog2 hits :fighter for 6
:fighter hits :orog2 for 13
:fighter misses :orog0
:cleric has no remaining spell slots to heal with
remaining hp: 45</code></pre>
</details>
<details>
<summary>46 hp</summary>
<pre><code>simulation# 9
encounter# 0
round# 0
:fighter misses :orog0
:fighter misses :orog0
the sorcerer uses shield to block :orog0
:orog0 misses :sorcerer
:orog0 crits :fighter for 18
:orog1 crits :fighter for 13
:orog1 hits :sorcerer for 15
:orog2 misses :fighter
:orog2 misses :cleric
:sorcerer hits :orog0 for 10
:cleric blesses :fighter :paladin :cleric
:paladin misses :orog0
:paladin misses :orog0
round# 1
:fighter hits :orog0 for 10
:fighter hits :orog0 for 14
:orog0 misses :paladin
:orog0 hits :sorcerer for 12
:orog1 misses :cleric
:orog1 hits :paladin for 12
:orog2 misses :fighter
the sorcerer uses shield to block :orog2
:orog2 misses :sorcerer
:sorcerer misses :orog0
:cleric misses :orog0
:paladin hits :orog0 for 9 #blessed
:paladin misses :orog1
round# 2
:fighter misses :orog1
:fighter hits :orog1 for 10
:orog1 hits :sorcerer for 16
:orog1 misses :fighter
:orog2 hits :fighter for 16
:orog2 misses :cleric
:cleric casts :spell-2 cure wound and heals :fighter from 0 to 12
:paladin hits :orog1 for 13
:paladin hits :orog1 for 11
round# 3
:fighter misses :orog1
:fighter misses :orog1
:orog1 hits :cleric for 15
:orog1 misses :fighter
:orog2 misses :fighter
:orog2 misses :fighter
:cleric misses :orog1
:paladin hits :orog1 for 11
:paladin hits :orog2 for 7
round# 4
:fighter misses :orog2
:fighter misses :orog2
:orog2 crits :cleric for 21
:orog2 misses :fighter
:cleric misses :orog2
:paladin crits :orog2 for 19
:paladin hits :orog2 for 12
:cleric has no remaining spell slots to heal with
remaining hp: 46</code></pre>
</details>
<details>
<summary>58 hp</summary>
<pre><code>simulation# 5
encounter# 0
round# 0
:orog0 hits :cleric for 5
:orog0 misses :cleric
:orog1 misses :fighter
:orog1 misses :paladin
:orog2 misses :paladin
:orog2 misses :sorcerer
:cleric blesses :fighter :paladin :cleric
:sorcerer crits :orog0 for 16
:fighter hits :orog0 for 12
:fighter misses :orog0
:paladin crits :orog0 for 9
:paladin hits :orog0 for 9
round# 1
:orog1 hits :fighter for 9
:orog1 misses :cleric
:orog2 hits :fighter for 10
:orog2 hits :fighter for 10
:cleric misses :orog1
:sorcerer misses :orog1
:fighter misses :orog1
:fighter hits :orog1 for 7
:paladin hits :orog1 for 14 #blessed
:paladin misses :orog1
round# 2
:orog1 misses :fighter
:orog1 hits :cleric for 11
:orog2 hits :fighter for 15
:orog2 hits :sorcerer for 9
:cleric casts :spell-2 cure wound and heals :fighter from 0 to 11
:sorcerer misses :orog1
:fighter hits :orog1 for 12
:fighter misses :orog1
:paladin hits :orog1 for 9
:paladin misses :orog1
round# 3
:orog1 misses :sorcerer
:orog1 misses :paladin
:orog2 hits :sorcerer for 11
:orog2 hits :fighter for 14
:cleric misses :orog1
:sorcerer misses :orog1
:paladin misses :orog1
:paladin hits :orog1 for 13
round# 4
:orog2 hits :paladin for 16
:orog2 hits :paladin for 10
:cleric hits :orog2 for 6 #blessed
:sorcerer hits :orog2 for 19
:paladin hits :orog2 for 9
:paladin misses :orog2
round# 5
:orog2 misses :paladin
:orog2 misses :cleric
:cleric hits :orog2 for 10 #blessed
:sorcerer misses :orog0
:paladin hits :orog0 for 14
:paladin hits :orog0 for 12
:cleric has no remaining spell slots to heal with
remaining hp: 58</code></pre>
</details>
<details>
<summary>71 hp</summary>
<pre><code>simulation# 7
encounter# 0
round# 0
:fighter misses :orog0
:fighter misses :orog0
:orog0 hits :cleric for 14
:orog0 misses :sorcerer
:orog1 misses :sorcerer
:orog1 hits :paladin for 5
:orog2 hits :sorcerer for 6
:orog2 hits :sorcerer for 15
:sorcerer hits :orog0 for 11
:paladin misses :orog0
:paladin misses :orog0
:cleric blesses :fighter :paladin :cleric
round# 1
:fighter hits :orog0 for 10
:fighter misses :orog0
:orog0 misses :paladin
:orog0 hits :paladin for 9
:orog1 misses :sorcerer
:orog1 misses :cleric
:orog2 hits :fighter for 6
:orog2 hits :fighter for 10
:sorcerer misses :orog0
:paladin misses :orog0
:paladin hits :orog0 for 14
:cleric hits :orog0 for 10
round# 2
:fighter hits :orog1 for 8
:fighter misses :orog1
:orog1 hits :cleric for 14
:orog1 misses :paladin
:orog2 misses :sorcerer
:orog2 hits :sorcerer for 6
:sorcerer hits :orog1 for 10
:paladin misses :orog1
:paladin misses :orog1
:cleric hits :orog1 for 4
round# 3
:fighter misses :orog1
:fighter misses :orog1
:orog1 hits :sorcerer for 6
the sorcerer uses shield to block :orog1
:orog1 misses :sorcerer
:orog2 misses :fighter
:orog2 hits :sorcerer for 15
:paladin hits :orog1 for 13
:paladin hits :orog1 for 12
:cleric casts :spell-2 cure wound and heals :sorcerer from 0 to 16
round# 4
:fighter hits :orog2 for 13 #blessed
:fighter hits :orog2 for 11
:orog2 misses :fighter
:orog2 hits :sorcerer for 13
:sorcerer misses :orog2
:paladin hits :orog2 for 10 #blessed
:paladin misses :orog2
:cleric hits :orog2 for 5
:cleric has no remaining spell slots to heal with
remaining hp: 71</code></pre>
</details>
<details>
<summary>76 hp</summary>
<pre><code>simulation# 8
encounter# 0
round# 0
:cleric blesses :fighter :paladin :cleric
:sorcerer hits :orog0 for 8
:paladin misses :orog0
:paladin hits :orog0 for 7
:orog0 misses :fighter
:orog0 misses :cleric
:orog1 misses :fighter
:orog1 hits :sorcerer for 15
:orog2 misses :sorcerer
:orog2 hits :fighter for 12
:fighter hits :orog0 for 10
:fighter misses :orog0
round# 1
:cleric hits :orog0 for 4 #blessed
:sorcerer misses :orog0
:paladin misses :orog0
:paladin crits :orog0 for 13
:orog1 misses :cleric
:orog1 crits :paladin for 23
:orog2 hits :paladin for 13
:orog2 misses :sorcerer
:fighter hits :orog1 for 10 #blessed
:fighter misses :orog1
round# 2
:cleric misses :orog1
:sorcerer hits :orog1 for 8
:paladin hits :orog1 for 12 #blessed
:paladin misses :orog1
the sorcerer uses shield to block :orog1
:orog1 misses :sorcerer
:orog1 hits :sorcerer for 11
:orog2 hits :fighter for 8
:orog2 misses :cleric
:fighter misses :orog1
:fighter hits :orog1 for 14
round# 3
:cleric misses :orog1
:sorcerer misses :orog1
:paladin crits :orog1 for 17
:paladin misses :orog2
:orog2 hits :sorcerer for 10
:orog2 hits :fighter for 5
:fighter crits :orog2 for 17
:fighter misses :orog2
round# 4
:cleric hits :orog2 for 4
:sorcerer misses :orog2
:paladin hits :orog2 for 11 #blessed
:paladin misses :orog2
:orog2 misses :sorcerer
:orog2 misses :paladin
:fighter hits :orog2 for 8
:fighter hits :orog0 for 7
:cleric casts :spell-2 cure wound and heals :sorcerer from 2 to 11
:cleric has no remaining spell slots to heal with
remaining hp: 76</code></pre>
</details>
<details>
<summary>76 hp</summary>
<pre><code>simulation# 1
encounter# 0
round# 0
:orog0 misses :cleric
:orog0 hits :paladin for 15
:orog1 misses :fighter
:orog1 misses :paladin
:orog2 misses :fighter
:orog2 misses :cleric
:sorcerer hits :orog0 for 16
:paladin misses :orog0
:paladin misses :orog0
:cleric blesses :fighter :paladin :cleric
:fighter hits :orog0 for 12
:fighter hits :orog0 for 12 #blessed
round# 1
:orog0 crits :paladin for 17
:orog0 hits :paladin for 14
:orog1 crits :fighter for 15
:orog1 hits :cleric for 11
:orog2 hits :cleric for 13
:orog2 misses :fighter
:sorcerer hits :orog0 for 13
:cleric casts :spell-2 cure wound and heals :paladin from 0 to 16
:fighter misses :orog1
:fighter hits :orog1 for 7
round# 2
the sorcerer uses shield to block :orog1
:orog1 misses :sorcerer
:orog1 misses :paladin
:orog2 hits :paladin for 7
:orog2 misses :sorcerer
:sorcerer hits :orog1 for 11
:paladin hits :orog1 for 14
:paladin hits :orog1 for 14 #blessed
:cleric misses :orog2
:fighter crits :orog2 for 17
:fighter hits :orog2 for 12
round# 3
:orog2 misses :fighter
:orog2 crits :cleric for 16
:sorcerer hits :orog2 for 10
:paladin misses :orog2
:paladin hits :orog2 for 11
:fighter hits :orog0 for 7 #blessed
:fighter hits :orog0 for 11
:cleric has no remaining spell slots to heal with
remaining hp: 76</code></pre>
</details>
<details>
<summary>93 hp</summary>
<pre><code>simulation# 3
encounter# 0
round# 0
:cleric blesses :fighter :paladin :cleric
:paladin hits :orog0 for 8
:paladin hits :orog0 for 10
:orog0 misses :paladin
:orog0 hits :cleric for 14
:orog1 misses :fighter
:orog1 hits :paladin for 14
:orog2 misses :paladin
:orog2 hits :paladin for 8
:fighter hits :orog0 for 10
:fighter crits :orog0 for 10
:sorcerer misses :orog0
round# 1
:cleric misses :orog0
:paladin hits :orog0 for 14
:paladin hits :orog1 for 8
:orog1 misses :paladin
the sorcerer uses shield to block :orog1
:orog1 misses :sorcerer
:orog2 misses :paladin
:orog2 misses :sorcerer
:fighter misses :orog1
:fighter misses :orog1
:sorcerer hits :orog1 for 16
round# 2
:cleric misses :orog1
:paladin misses :orog1
:paladin hits :orog1 for 12
:orog1 misses :cleric
:orog1 hits :cleric for 16
the sorcerer uses shield to block :orog2
:orog2 misses :sorcerer
:orog2 misses :paladin
:fighter hits :orog1 for 10
:fighter hits :orog1 for 13 #blessed
:sorcerer hits :orog2 for 9
round# 3
:cleric misses :orog2
:paladin misses :orog2
:paladin misses :orog2
:orog2 hits :sorcerer for 15
:orog2 hits :paladin for 16
:fighter misses :orog2
:fighter hits :orog2 for 8 #blessed
:sorcerer hits :orog2 for 8
round# 4
:cleric hits :orog2 for 7
:paladin misses :orog2
:paladin hits :orog2 for 11
:fighter hits :orog0 for 8
:fighter misses :orog0
:sorcerer misses :orog0
:cleric casts :spell-2 cure wound and heals :paladin from 6 to 18
:cleric has no remaining spell slots to heal with
remaining hp: 93</code></pre>
</details>
<details>
<summary>118 hp</summary>
<pre><code>simulation# 6
encounter# 0
round# 0
:orog0 misses :fighter
the sorcerer uses shield to block :orog0
:orog0 misses :sorcerer
:orog1 misses :cleric
:orog1 hits :paladin for 9
:orog2 hits :paladin for 7
:orog2 misses :cleric
:fighter misses :orog0
:fighter misses :orog0
:sorcerer hits :orog0 for 17
:paladin misses :orog0
:paladin misses :orog0
:cleric blesses :fighter :paladin :cleric
round# 1
:orog0 hits :fighter for 6
:orog0 hits :paladin for 10
:orog1 hits :paladin for 16
:orog1 misses :sorcerer
:orog2 misses :sorcerer
:orog2 misses :cleric
:fighter hits :orog0 for 9
:fighter misses :orog0
:sorcerer misses :orog0
:paladin hits :orog0 for 11
:paladin hits :orog0 for 7 #blessed
:cleric hits :orog1 for 8
round# 2
:orog1 misses :paladin
:orog1 misses :fighter
:orog2 misses :sorcerer
:orog2 misses :sorcerer
:fighter hits :orog1 for 8
:fighter misses :orog1
:sorcerer hits :orog1 for 9
:paladin hits :orog1 for 14
:paladin misses :orog1
:cleric hits :orog1 for 11
round# 3
:orog2 misses :paladin
the sorcerer uses shield to block :orog2
:orog2 misses :sorcerer
:fighter misses :orog2
:fighter misses :orog2
:sorcerer misses :orog2
:paladin misses :orog2
:paladin hits :orog2 for 8 #blessed
:cleric crits :orog2 for 10
round# 4
the sorcerer uses shield to block :orog2
:orog2 misses :sorcerer
:orog2 misses :sorcerer
:fighter misses :orog2
:fighter misses :orog2
:sorcerer hits :orog2 for 7
:paladin hits :orog2 for 8
:paladin misses :orog2
:cleric hits :orog2 for 5
round# 5
:orog2 hits :paladin for 16
:orog2 hits :sorcerer for 15
:fighter hits :orog2 for 8
:fighter hits :orog0 for 9
:sorcerer misses :orog0
:cleric casts :spell-2 cure wound and heals :paladin from 0 to 19
:cleric has no remaining spell slots to heal with
remaining hp: 118</code></pre>
</details>
<details>
<summary>119 hp</summary>
<pre><code>simulation# 2
encounter# 0
round# 0
:fighter misses :orog0
:fighter hits :orog0 for 12
:cleric blesses :fighter :paladin :cleric
:orog0 misses :cleric
:orog0 misses :cleric
:orog1 hits :paladin for 5
the sorcerer uses shield to block :orog1
:orog1 misses :sorcerer
:orog2 misses :fighter
:orog2 hits :paladin for 9
:sorcerer hits :orog0 for 12
:paladin hits :orog0 for 8 #blessed
:paladin hits :orog0 for 13
round# 1
:fighter misses :orog1
:fighter misses :orog1
:cleric misses :orog1
:orog1 hits :cleric for 13
:orog1 hits :cleric for 8
:orog2 hits :fighter for 13
:orog2 misses :fighter
:sorcerer misses :orog1
:paladin hits :orog1 for 11
:paladin hits :orog1 for 11
round# 2
:fighter crits :orog1 for 13
:fighter hits :orog1 for 13
:cleric hits :orog2 for 9 #blessed
:orog2 misses :paladin
:orog2 hits :paladin for 15
:sorcerer hits :orog2 for 13
:paladin misses :orog2
:paladin hits :orog2 for 12
round# 3
:fighter misses :orog2
:fighter hits :orog2 for 12
:cleric misses :orog0
:sorcerer hits :orog0 for 9
:paladin misses :orog0
:paladin hits :orog0 for 7
:cleric casts :spell-2 cure wound and heals :paladin from 15 to 33
:cleric has no remaining spell slots to heal with
remaining hp: 119</code></pre>
</details>
<details>
<summary>120 hp</summary>
<pre><code>simulation# 10
encounter# 0
round# 0
:paladin misses :orog0
:paladin hits :orog0 for 9
:sorcerer hits :orog0 for 11
:fighter hits :orog0 for 14
:fighter hits :orog0 for 12
:orog0 hits :fighter for 14
:orog0 misses :paladin
:orog1 misses :fighter
:orog1 hits :paladin for 16
:orog2 misses :fighter
:orog2 hits :sorcerer for 13
:cleric blesses :fighter :paladin :cleric
round# 1
:paladin misses :orog0
:paladin hits :orog0 for 12
:sorcerer hits :orog1 for 11
:fighter misses :orog1
:fighter hits :orog1 for 9
:orog1 hits :sorcerer for 6
:orog1 misses :fighter
:orog2 misses :paladin
:orog2 hits :fighter for 5
:cleric hits :orog1 for 7
round# 2
:paladin hits :orog1 for 9
:paladin hits :orog1 for 11
:sorcerer hits :orog1 for 12
:fighter hits :orog2 for 8
:fighter hits :orog2 for 7
:orog2 misses :paladin
:orog2 misses :cleric
:cleric hits :orog2 for 6
round# 3
:paladin hits :orog2 for 7
:paladin hits :orog2 for 7
:sorcerer hits :orog2 for 5
:fighter misses :orog0
:fighter misses :orog0
:cleric hits :orog0 for 11
:cleric casts :spell-2 cure wound and heals :fighter from 25 to 35
:cleric has no remaining spell slots to heal with
remaining hp: 120</code></pre>
</details>
<details>
<summary>121 hp</summary>
<pre><code>simulation# 4
encounter# 0
round# 0
:cleric blesses :fighter :paladin :cleric
:orog0 misses :sorcerer
:orog0 misses :paladin
the sorcerer uses shield to block :orog1
:orog1 misses :sorcerer
:orog1 misses :paladin
:orog2 misses :sorcerer
:orog2 misses :cleric
:sorcerer hits :orog0 for 9
:fighter misses :orog0
:fighter hits :orog0 for 7
:paladin hits :orog0 for 13
:paladin hits :orog0 for 7 #blessed
round# 1
:cleric misses :orog0
:orog0 crits :paladin for 17
:orog0 crits :paladin for 14
:orog1 crits :paladin for 22
:orog1 hits :cleric for 8
:orog2 misses :fighter
the sorcerer uses shield to block :orog2
:orog2 misses :sorcerer
:sorcerer hits :orog0 for 9
:fighter hits :orog0 for 13 #blessed
:fighter hits :orog1 for 7
round# 2
:cleric casts :spell-2 cure wound and heals :paladin from 0 to 16
:orog1 misses :cleric
:orog1 misses :cleric
:orog2 misses :paladin
:orog2 misses :cleric
:sorcerer misses :orog1
:fighter misses :orog1
:fighter misses :orog1
:paladin hits :orog1 for 7
:paladin misses :orog1
round# 3
:cleric hits :orog1 for 7
:orog1 misses :cleric
:orog1 misses :fighter
:orog2 hits :paladin for 7
the sorcerer uses shield to block :orog2
:orog2 misses :sorcerer
:sorcerer hits :orog1 for 11
:fighter misses :orog1
:fighter hits :orog1 for 13
:paladin hits :orog1 for 13
:paladin hits :orog2 for 12
round# 4
:cleric hits :orog2 for 8 #blessed
the sorcerer uses shield to block :orog2
:orog2 misses :sorcerer
:orog2 misses :paladin
:sorcerer misses :orog2
:fighter hits :orog2 for 13
:fighter misses :orog2
:paladin hits :orog2 for 13
:paladin hits :orog0 for 14
:cleric has no remaining spell slots to heal with
remaining hp: 121</code></pre>
</details>

#### No bless at all. Average across 3000 sims: 99.2 hp
<details>
<summary>52 hp</summary>
<pre><code>simulation# 8
encounter# 0
round# 0
:sorcerer misses :orog0
:paladin hits :orog0 for 11
:paladin misses :orog0
:orog0 hits :sorcerer for 10
:orog0 crits :paladin for 6
:orog1 hits :cleric for 15
:orog1 misses :fighter
:orog2 hits :fighter for 11
:orog2 hits :cleric for 6
:fighter misses :orog0
:fighter misses :orog0
:cleric hits :orog0 for 9
round# 1
:sorcerer misses :orog0
:paladin misses :orog0
:paladin hits :orog0 for 13
:orog0 misses :fighter
:orog0 hits :paladin for 11
:orog1 misses :fighter
:orog1 misses :cleric
:orog2 misses :paladin
:orog2 misses :fighter
:fighter hits :orog0 for 10
:fighter hits :orog0 for 8
:cleric hits :orog1 for 7
round# 2
:sorcerer misses :orog1
:paladin misses :orog1
:paladin hits :orog1 for 9
:orog1 crits :paladin for 12
:orog1 hits :fighter for 12
:orog2 hits :cleric for 10
:orog2 misses :sorcerer
:fighter misses :orog1
:fighter misses :orog1
:cleric misses :orog1
round# 3
:sorcerer misses :orog1
:paladin misses :orog1
:paladin misses :orog1
:orog1 hits :paladin for 11
:orog1 hits :paladin for 5
:orog2 misses :fighter
:orog2 misses :cleric
:fighter crits :orog1 for 15
:fighter misses :orog1
:cleric casts :spell-1 cure wound and heals :paladin from 0 to 10
round# 4
:sorcerer hits :orog1 for 12
:paladin hits :orog1 for 14
:paladin misses :orog2
:orog2 hits :sorcerer for 10
:orog2 hits :cleric for 15
:fighter misses :orog2
:fighter misses :orog2
round# 5
:sorcerer misses :orog2
:paladin hits :orog2 for 12
:paladin hits :orog2 for 8
:orog2 misses :fighter
:orog2 crits :sorcerer for 10
:fighter hits :orog2 for 13
:fighter hits :orog2 for 11
round# 6
:sorcerer hits :orog2 for 3
:paladin hits :orog2 for 7
:paladin misses :orog0
:fighter misses :orog0
:fighter hits :orog0 for 10
:cleric casts :spell-2 cure wound and heals :cleric from 0 to 13
:cleric has no remaining spell slots to heal with
remaining hp: 52</code></pre>
</details>
<details>
<summary>62 hp</summary>
<pre><code>simulation# 5
encounter# 0
round# 0
:orog0 hits :fighter for 15
:orog0 misses :cleric
:orog1 misses :sorcerer
:orog1 crits :cleric for 17
:orog2 misses :paladin
:orog2 misses :paladin
:fighter misses :orog0
:fighter misses :orog0
:paladin misses :orog0
:paladin hits :orog0 for 7
:cleric hits :orog0 for 7
:sorcerer misses :orog0
round# 1
:orog0 misses :paladin
:orog0 hits :cleric for 11
:orog1 misses :cleric
:orog1 misses :fighter
:orog2 misses :cleric
:orog2 hits :sorcerer for 7
:fighter hits :orog0 for 12
:fighter hits :orog0 for 7
:paladin misses :orog0
:paladin misses :orog0
:cleric misses :orog0
:sorcerer hits :orog0 for 11
round# 2
:orog1 misses :cleric
:orog1 hits :sorcerer for 6
:orog2 crits :paladin for 14
:orog2 hits :cleric for 10
:fighter hits :orog1 for 9
:fighter hits :orog1 for 7
:paladin misses :orog1
:paladin misses :orog1
:sorcerer misses :orog1
round# 3
:orog1 hits :paladin for 7
:orog1 hits :paladin for 15
:orog2 hits :paladin for 14
the sorcerer uses shield to block :orog2
:orog2 misses :sorcerer
:fighter hits :orog1 for 9
:fighter misses :orog1
:sorcerer crits :orog1 for 26
round# 4
the sorcerer uses shield to block :orog2
:orog2 misses :sorcerer
:orog2 misses :sorcerer
:fighter hits :orog2 for 9
:fighter misses :orog2
:sorcerer misses :orog2
round# 5
the sorcerer uses shield to block :orog2
:orog2 misses :sorcerer
:orog2 misses :sorcerer
:fighter misses :orog2
:fighter misses :orog2
:sorcerer hits :orog2 for 16
round# 6
the sorcerer uses shield to block :orog2
:orog2 misses :sorcerer
:orog2 hits :fighter for 16
:fighter hits :orog2 for 11
:fighter crits :orog2 for 9
:sorcerer hits :orog0 for 6
:cleric casts :spell-1 cure wound and heals :paladin from 0 to 9
:cleric casts :spell-2 cure wound and heals :cleric from 0 to 15
:cleric has no remaining spell slots to heal with
remaining hp: 62</code></pre>
</details>
<details>
<summary>73 hp</summary>
<pre><code>simulation# 6
encounter# 0
round# 0
:cleric misses :orog0
:orog0 hits :cleric for 8
:orog0 hits :fighter for 8
:orog1 misses :paladin
:orog1 crits :sorcerer for 19
:orog2 hits :paladin for 7
:orog2 hits :cleric for 10
:paladin crits :orog0 for 13
:paladin hits :orog0 for 12
:fighter hits :orog0 for 7
:fighter hits :orog0 for 14
:sorcerer hits :orog1 for 8
round# 1
:cleric misses :orog1
:orog1 hits :cleric for 13
:orog1 misses :paladin
:orog2 hits :cleric for 8
:orog2 misses :sorcerer
:paladin misses :orog1
:paladin hits :orog1 for 10
:fighter misses :orog1
:fighter hits :orog1 for 12
:sorcerer misses :orog1
round# 2
the sorcerer uses shield to block :orog1
:orog1 misses :sorcerer
:orog1 misses :fighter
:orog2 crits :sorcerer for 20
:orog2 hits :fighter for 9
:paladin hits :orog1 for 10
:paladin misses :orog1
:fighter crits :orog1 for 10
:fighter misses :orog2
round# 3
:orog2 misses :fighter
:orog2 misses :fighter
:paladin crits :orog2 for 16
:paladin hits :orog2 for 9
:fighter misses :orog2
:fighter hits :orog2 for 11
round# 4
:orog2 hits :paladin for 13
:orog2 misses :fighter
:paladin hits :orog2 for 14
:paladin hits :orog0 for 8
:fighter hits :orog0 for 8
:fighter misses :orog0
:cleric casts :spell-1 cure wound and heals :cleric from 0 to 5
:cleric casts :spell-2 cure wound and heals :sorcerer from 0 to 17
:cleric has no remaining spell slots to heal with
remaining hp: 73</code></pre>
</details>
<details>
<summary>88 hp</summary>
<pre><code>simulation# 2
encounter# 0
round# 0
:cleric misses :orog0
:paladin misses :orog0
:paladin hits :orog0 for 7
:orog0 misses :fighter
:orog0 hits :fighter for 13
:orog1 misses :paladin
:orog1 hits :cleric for 11
:orog2 misses :cleric
the sorcerer uses shield to block :orog2
:orog2 misses :sorcerer
:fighter hits :orog0 for 12
:fighter misses :orog0
:sorcerer misses :orog0
round# 1
:cleric misses :orog0
:paladin misses :orog0
:paladin misses :orog0
:orog0 misses :paladin
:orog0 misses :cleric
:orog1 hits :fighter for 9
:orog1 hits :cleric for 13
:orog2 hits :sorcerer for 12
:orog2 hits :cleric for 15
:fighter hits :orog0 for 9
:fighter hits :orog0 for 9
:sorcerer hits :orog1 for 5
round# 2
:paladin misses :orog1
:paladin misses :orog1
:orog1 misses :fighter
the sorcerer uses shield to block :orog1
:orog1 misses :sorcerer
:orog2 misses :sorcerer
:orog2 hits :fighter for 7
:fighter hits :orog1 for 13
:fighter hits :orog1 for 12
:sorcerer hits :orog1 for 8
round# 3
:paladin hits :orog1 for 7
:paladin misses :orog2
:orog2 misses :sorcerer
:orog2 crits :sorcerer for 23
:fighter misses :orog2
:fighter hits :orog2 for 11
:sorcerer hits :orog2 for 20
round# 4
:paladin misses :orog2
:paladin hits :orog2 for 10
:fighter hits :orog0 for 13
:fighter misses :orog0
:sorcerer hits :orog0 for 7
:cleric casts :spell-1 cure wound and heals :cleric from 0 to 9
:cleric casts :spell-2 cure wound and heals :sorcerer from 3 to 20
:cleric has no remaining spell slots to heal with
remaining hp: 88</code></pre>
</details>
<details>
<summary>90 hp</summary>
<pre><code>simulation# 4
encounter# 0
round# 0
:cleric hits :orog0 for 8
:sorcerer hits :orog0 for 5
:orog0 misses :paladin
:orog0 hits :fighter for 6
:orog1 misses :paladin
:orog1 misses :fighter
:orog2 misses :fighter
:orog2 misses :paladin
:paladin misses :orog0
:paladin hits :orog0 for 14
:fighter misses :orog0
:fighter hits :orog0 for 12
round# 1
:cleric hits :orog0 for 10
:sorcerer misses :orog1
:orog1 hits :sorcerer for 12
:orog1 hits :paladin for 13
:orog2 hits :cleric for 11
:orog2 hits :paladin for 11
:paladin hits :orog1 for 7
:paladin misses :orog1
:fighter crits :orog1 for 20
:fighter misses :orog1
round# 2
:cleric hits :orog1 for 4
:sorcerer misses :orog1
:orog1 misses :cleric
:orog1 misses :sorcerer
:orog2 hits :paladin for 13
:orog2 hits :paladin for 6
:paladin misses :orog1
:paladin misses :orog1
:fighter misses :orog1
:fighter misses :orog1
round# 3
:cleric misses :orog1
:sorcerer misses :orog1
:orog1 misses :cleric
:orog1 hits :cleric for 8
:orog2 misses :sorcerer
:orog2 misses :cleric
:paladin misses :orog1
:paladin hits :orog1 for 12
:fighter misses :orog2
:fighter hits :orog2 for 10
round# 4
:cleric misses :orog2
:sorcerer misses :orog2
the sorcerer uses shield to block :orog2
:orog2 misses :sorcerer
:orog2 misses :fighter
:paladin hits :orog2 for 8
:paladin misses :orog2
:fighter misses :orog2
:fighter misses :orog2
round# 5
:cleric misses :orog2
:sorcerer misses :orog2
:orog2 hits :cleric for 7
:orog2 hits :cleric for 8
:paladin crits :orog2 for 15
:paladin hits :orog2 for 8
:fighter hits :orog0 for 14
:fighter misses :orog0
:cleric casts :spell-1 cure wound and heals :paladin from 1 to 7
:cleric casts :spell-2 cure wound and heals :paladin from 7 to 22
:cleric has no remaining spell slots to heal with
remaining hp: 90</code></pre>
</details>
<details>
<summary>100 hp</summary>
<pre><code>simulation# 3
encounter# 0
round# 0
:sorcerer misses :orog0
:orog0 hits :fighter for 16
:orog0 misses :sorcerer
:orog1 hits :sorcerer for 10
:orog1 misses :fighter
:orog2 misses :cleric
:orog2 misses :sorcerer
:cleric misses :orog0
:fighter hits :orog0 for 12
:fighter misses :orog0
:paladin misses :orog0
:paladin misses :orog0
round# 1
:sorcerer misses :orog0
:orog0 misses :paladin
:orog0 misses :paladin
:orog1 crits :sorcerer for 20
:orog1 misses :sorcerer
:orog2 hits :paladin for 14
:orog2 misses :paladin
:cleric hits :orog0 for 6
:fighter hits :orog0 for 12
:fighter hits :orog0 for 13
:paladin hits :orog1 for 9
:paladin misses :orog1
round# 2
:sorcerer hits :orog1 for 17
:orog1 misses :cleric
:orog1 hits :sorcerer for 8
:orog2 misses :cleric
:orog2 hits :cleric for 8
:cleric casts :spell-1 cure wound and heals :sorcerer from 0 to 10
:fighter hits :orog1 for 8
:fighter hits :orog1 for 10
:paladin hits :orog1 for 9
:paladin misses :orog2
round# 3
:sorcerer misses :orog2
:orog2 misses :sorcerer
:orog2 hits :paladin for 9
:cleric hits :orog2 for 6
:fighter misses :orog2
:fighter misses :orog2
:paladin hits :orog2 for 14
:paladin hits :orog2 for 11
round# 4
:sorcerer hits :orog2 for 16
:cleric hits :orog0 for 7
:fighter hits :orog0 for 12
:fighter hits :orog0 for 14
:paladin hits :orog0 for 9
:paladin misses :orog0
:cleric casts :spell-2 cure wound and heals :sorcerer from 10 to 21
:cleric has no remaining spell slots to heal with
remaining hp: 100</code></pre>
</details>
<details>
<summary>103 hp</summary>
<pre><code>simulation# 10
encounter# 0
round# 0
:paladin hits :orog0 for 14
:paladin misses :orog0
:fighter misses :orog0
:fighter hits :orog0 for 14
the sorcerer uses shield to block :orog0
:orog0 misses :sorcerer
:orog0 hits :fighter for 13
:orog1 misses :sorcerer
:orog1 hits :fighter for 7
:orog2 hits :cleric for 16
:orog2 hits :cleric for 8
:cleric hits :orog0 for 10
:sorcerer hits :orog1 for 14
round# 1
:paladin misses :orog1
:paladin hits :orog1 for 8
:fighter crits :orog1 for 20
:fighter misses :orog1
:orog1 hits :fighter for 9
:orog1 misses :paladin
:orog2 misses :sorcerer
:orog2 hits :fighter for 15
:cleric casts :spell-1 cure wound and heals :fighter from 0 to 8
:sorcerer hits :orog1 for 5
round# 2
:paladin misses :orog2
:paladin misses :orog2
:fighter hits :orog2 for 12
:fighter crits :orog2 for 11
:orog2 hits :fighter for 6
:orog2 crits :cleric for 7
:cleric misses :orog2
:sorcerer misses :orog2
round# 3
:paladin misses :orog2
:paladin hits :orog2 for 11
:fighter misses :orog2
:fighter hits :orog2 for 13
:cleric misses :orog0
:sorcerer hits :orog0 for 8
:cleric casts :spell-2 cure wound and heals :fighter from 2 to 14
:cleric has no remaining spell slots to heal with
remaining hp: 103</code></pre>
</details>
<details>
<summary>119 hp</summary>
<pre><code>simulation# 9
encounter# 0
round# 0
:orog0 hits :sorcerer for 12
:orog0 misses :cleric
:orog1 misses :fighter
:orog1 misses :cleric
:orog2 hits :paladin for 14
:orog2 misses :cleric
:cleric crits :orog0 for 14
:fighter misses :orog0
:fighter misses :orog0
:sorcerer hits :orog0 for 10
:paladin hits :orog0 for 12
:paladin hits :orog0 for 10
round# 1
the sorcerer uses shield to block :orog0
:orog0 misses :sorcerer
:orog0 misses :paladin
:orog1 hits :sorcerer for 13
:orog1 hits :fighter for 16
:orog2 misses :fighter
:orog2 misses :fighter
:cleric hits :orog0 for 6
:fighter hits :orog0 for 9
:fighter misses :orog1
:sorcerer misses :orog1
:paladin misses :orog1
:paladin hits :orog1 for 12
round# 2
:orog1 misses :sorcerer
:orog1 misses :paladin
:orog2 misses :cleric
:orog2 hits :sorcerer for 9
:cleric misses :orog1
:fighter hits :orog1 for 12
:fighter hits :orog1 for 7
:sorcerer misses :orog1
:paladin hits :orog1 for 13
:paladin misses :orog1
round# 3
:orog1 hits :paladin for 7
:orog1 misses :cleric
:orog2 misses :fighter
:orog2 misses :paladin
:cleric misses :orog1
:fighter misses :orog1
:fighter misses :orog1
:sorcerer hits :orog1 for 16
:paladin hits :orog2 for 9
:paladin crits :orog2 for 16
round# 4
:orog2 misses :paladin
the sorcerer uses shield to block :orog2
:orog2 misses :sorcerer
:cleric misses :orog2
:fighter hits :orog2 for 10
:fighter misses :orog2
:sorcerer misses :orog2
:paladin misses :orog2
:paladin misses :orog2
round# 5
:orog2 misses :fighter
:orog2 misses :paladin
:cleric misses :orog2
:fighter hits :orog2 for 14
:fighter hits :orog0 for 14
:sorcerer hits :orog0 for 9
:paladin misses :orog0
:paladin hits :orog0 for 11
:cleric casts :spell-1 cure wound and heals :sorcerer from 4 to 14
:cleric casts :spell-2 cure wound and heals :sorcerer from 14 to 30
:cleric has no remaining spell slots to heal with
remaining hp: 119</code></pre>
</details>
<details>
<summary>119 hp</summary>
<pre><code>simulation# 0
encounter# 0
round# 0
:cleric hits :orog0 for 5
:orog0 hits :paladin for 13
:orog0 misses :fighter
:orog1 hits :paladin for 9
:orog1 misses :fighter
:orog2 hits :sorcerer for 9
:orog2 misses :cleric
:paladin hits :orog0 for 14
:paladin hits :orog0 for 9
:fighter misses :orog0
:fighter hits :orog0 for 10
:sorcerer misses :orog0
round# 1
:cleric hits :orog0 for 9
:orog1 hits :fighter for 9
:orog1 misses :cleric
the sorcerer uses shield to block :orog2
:orog2 misses :sorcerer
:orog2 misses :cleric
:paladin misses :orog1
:paladin crits :orog1 for 16
:fighter misses :orog1
:fighter hits :orog1 for 7
:sorcerer hits :orog1 for 12
round# 2
:cleric hits :orog1 for 7
the sorcerer uses shield to block :orog1
:orog1 misses :sorcerer
:orog1 misses :paladin
:orog2 misses :paladin
:orog2 misses :cleric
:paladin misses :orog1
:paladin misses :orog1
:fighter misses :orog1
:fighter misses :orog1
:sorcerer misses :orog1
round# 3
:cleric misses :orog1
:orog1 misses :paladin
:orog1 misses :sorcerer
:orog2 hits :fighter for 6
:orog2 hits :sorcerer for 11
:paladin crits :orog1 for 18
:paladin hits :orog2 for 14
:fighter misses :orog2
:fighter hits :orog2 for 14
:sorcerer hits :orog2 for 6
round# 4
:cleric misses :orog2
:orog2 misses :paladin
:orog2 hits :fighter for 9
:paladin hits :orog2 for 10
:paladin misses :orog2
:fighter misses :orog2
:fighter hits :orog2 for 9
:sorcerer hits :orog0 for 12
:cleric casts :spell-1 cure wound and heals :fighter from 20 to 28
:cleric casts :spell-2 cure wound and heals :paladin from 22 to 35
:cleric has no remaining spell slots to heal with
remaining hp: 119</code></pre>
</details>
<details>
<summary>139 hp</summary>
<pre><code>simulation# 7
encounter# 0
round# 0
:cleric misses :orog0
:sorcerer hits :orog0 for 9
:fighter crits :orog0 for 17
:fighter hits :orog0 for 7
:orog0 crits :cleric for 23
:orog0 misses :paladin
:orog1 hits :cleric for 13
the sorcerer uses shield to block :orog1
:orog1 misses :sorcerer
:orog2 misses :paladin
:orog2 misses :paladin
:paladin hits :orog0 for 9
:paladin hits :orog1 for 11
round# 1
:cleric misses :orog1
:sorcerer misses :orog1
:fighter crits :orog1 for 11
:fighter hits :orog1 for 13
:orog1 misses :cleric
:orog1 misses :paladin
:orog2 hits :fighter for 9
:orog2 misses :paladin
:paladin misses :orog1
:paladin hits :orog1 for 7
round# 2
:cleric hits :orog2 for 9
:sorcerer misses :orog2
:fighter hits :orog2 for 14
:fighter misses :orog2
:orog2 misses :cleric
:orog2 misses :sorcerer
:paladin misses :orog2
:paladin hits :orog2 for 13
round# 3
:cleric hits :orog2 for 6
:sorcerer hits :orog2 for 11
:fighter misses :orog0
:fighter crits :orog0 for 10
:paladin hits :orog0 for 8
:paladin hits :orog0 for 9
:cleric casts :spell-1 cure wound and heals :cleric from 2 to 7
:cleric casts :spell-2 cure wound and heals :cleric from 7 to 22
:cleric has no remaining spell slots to heal with
remaining hp: 139</code></pre>
</details>
<details>
<summary>150 hp</summary>
<pre><code>simulation# 1
encounter# 0
round# 0
:paladin hits :orog0 for 8
:paladin hits :orog0 for 11
:fighter misses :orog0
:fighter hits :orog0 for 12
:sorcerer misses :orog0
:orog0 misses :fighter
:orog0 misses :paladin
:orog1 hits :paladin for 9
:orog1 hits :sorcerer for 7
:orog2 misses :paladin
:orog2 misses :paladin
:cleric hits :orog0 for 10
round# 1
:paladin misses :orog0
:paladin misses :orog0
:fighter crits :orog0 for 15
:fighter misses :orog1
:sorcerer hits :orog1 for 10
:orog1 misses :sorcerer
:orog1 hits :fighter for 5
:orog2 hits :cleric for 5
:orog2 misses :fighter
:cleric misses :orog1
round# 2
:paladin misses :orog1
:paladin hits :orog1 for 7
:fighter hits :orog1 for 12
:fighter hits :orog1 for 13
:sorcerer misses :orog1
:orog1 hits :cleric for 5
:orog1 misses :paladin
:orog2 misses :fighter
:orog2 misses :sorcerer
:cleric misses :orog1
round# 3
:paladin misses :orog1
:paladin hits :orog1 for 10
:fighter hits :orog2 for 10
:fighter misses :orog2
:sorcerer misses :orog2
:orog2 misses :sorcerer
:orog2 misses :paladin
:cleric misses :orog2
round# 4
:paladin crits :orog2 for 16
:paladin hits :orog2 for 7
:fighter hits :orog0 for 10
:fighter misses :orog0
:sorcerer hits :orog0 for 12
:cleric hits :orog0 for 6
:cleric casts :spell-1 cure wound and heals :cleric from 28 to 36
:cleric casts :spell-2 cure wound and heals :paladin from 35 to 44
:cleric has no remaining spell slots to heal with
remaining hp: 150</code></pre>
</details>

#### Blessing before combat. Average across 3000 sims: 104.6
<details>
<summary>60 hp</summary>
<pre><code>simulation# 9
encounter# 0
:cleric blesses :fighter :paladin :cleric
round# 0
:orog0 hits :fighter for 10
:orog0 hits :sorcerer for 12
:orog1 crits :fighter for 20
:orog1 hits :cleric for 8
the sorcerer uses shield to block :orog2
:orog2 misses :sorcerer
:orog2 hits :fighter for 9
:cleric hits :orog0 for 4 #blessed
:paladin hits :orog0 for 7
:paladin misses :orog0
:sorcerer misses :orog0
:fighter misses :orog0
:fighter hits :orog0 for 14
round# 1
:orog0 misses :cleric
:orog0 hits :paladin for 7
:orog1 hits :cleric for 15
:orog1 misses :fighter
:orog2 misses :fighter
:orog2 hits :cleric for 5
:cleric misses :orog0
:paladin hits :orog0 for 8
:paladin hits :orog0 for 7
:sorcerer hits :orog1 for 10
:fighter misses :orog1
:fighter hits :orog1 for 10 #blessed
round# 2
:orog1 misses :paladin
:orog1 hits :sorcerer for 11
:orog2 misses :fighter
:orog2 misses :cleric
:cleric hits :orog1 for 11
:paladin hits :orog1 for 14
:paladin hits :orog2 for 12
:sorcerer misses :orog2
:fighter hits :orog2 for 8
:fighter misses :orog2
round# 3
:orog2 hits :sorcerer for 8
:orog2 crits :fighter for 14
:cleric casts :spell-2 cure wound and heals :fighter from 0 to 6
:paladin hits :orog2 for 13 #blessed
:paladin crits :orog2 for 13
:sorcerer hits :orog0 for 16
:fighter hits :orog0 for 13 #blessed
:fighter hits :orog0 for 11
:cleric has no remaining spell slots to heal with
remaining hp: 60</code></pre>
</details>
<details>
<summary>70 hp</summary>
<pre><code>simulation# 2
encounter# 0
:cleric blesses :fighter :paladin :cleric
round# 0
:orog0 misses :sorcerer
:orog0 misses :fighter
:orog1 hits :sorcerer for 5
:orog1 hits :fighter for 16
:orog2 hits :paladin for 16
:orog2 hits :cleric for 9
:cleric misses :orog0
:sorcerer misses :orog0
:paladin misses :orog0
:paladin hits :orog0 for 9
:fighter hits :orog0 for 10
:fighter misses :orog0
round# 1
the sorcerer uses shield to block :orog0
:orog0 misses :sorcerer
:orog0 hits :cleric for 5
:orog1 misses :cleric
:orog1 misses :fighter
:orog2 misses :cleric
:orog2 misses :paladin
:cleric misses :orog0
:sorcerer hits :orog0 for 11
:paladin hits :orog0 for 11 #blessed
:paladin misses :orog0
:fighter misses :orog0
:fighter hits :orog0 for 9
round# 2
:orog1 hits :paladin for 7
:orog1 misses :fighter
:orog2 misses :paladin
:orog2 crits :sorcerer for 18
:cleric hits :orog1 for 7
:sorcerer misses :orog1
:paladin hits :orog1 for 13
:paladin misses :orog1
:fighter hits :orog1 for 12
:fighter hits :orog1 for 14
round# 3
:orog2 hits :cleric for 15
:orog2 hits :sorcerer for 11
:cleric hits :orog2 for 6
:sorcerer misses :orog2
:paladin misses :orog2
:paladin misses :orog2
:fighter hits :orog2 for 11
:fighter misses :orog2
round# 4
:orog2 misses :paladin
:orog2 misses :cleric
:cleric misses :orog2
:sorcerer misses :orog2
:paladin misses :orog2
:paladin hits :orog2 for 7
:fighter hits :orog2 for 14 #blessed
:fighter hits :orog2 for 11 #blessed
:cleric casts :spell-2 cure wound and heals :sorcerer from 4 to 12
:cleric has no remaining spell slots to heal with
remaining hp: 70</code></pre>
</details>
<details>
<summary>80 hp</summary>
<pre><code>simulation# 1
encounter# 0
:cleric blesses :fighter :paladin :cleric
round# 0
:orog0 hits :cleric for 16
the sorcerer uses shield to block :orog0
:orog0 misses :sorcerer
:orog1 crits :cleric for 20
:orog1 hits :paladin for 10
:orog2 misses :sorcerer
:orog2 misses :sorcerer
:fighter hits :orog0 for 10 #blessed
:fighter misses :orog0
:sorcerer misses :orog0
:paladin crits :orog0 for 15
:paladin misses :orog0
:cleric misses :orog0
round# 1
:orog0 hits :cleric for 11
:orog0 misses :fighter
:orog1 hits :sorcerer for 16
:orog1 misses :paladin
:orog2 misses :paladin
:orog2 hits :sorcerer for 6
:fighter hits :orog0 for 14 #blessed
:fighter hits :orog1 for 7
:sorcerer hits :orog1 for 13
:paladin crits :orog1 for 9
:paladin misses :orog1
round# 2
:orog1 misses :sorcerer
the sorcerer uses shield to block :orog1
:orog1 misses :sorcerer
:orog2 misses :paladin
:orog2 hits :fighter for 11
:fighter hits :orog1 for 9
:fighter hits :orog1 for 10
:sorcerer misses :orog1
:paladin misses :orog1
:paladin hits :orog1 for 9 #blessed
round# 3
:orog2 misses :sorcerer
:orog2 hits :sorcerer for 11
:fighter crits :orog2 for 17
:fighter misses :orog2
:sorcerer misses :orog2
:paladin misses :orog2
:paladin hits :orog2 for 12
round# 4
:orog2 misses :paladin
:orog2 hits :sorcerer for 11
:fighter hits :orog2 for 13
:fighter misses :orog2
:paladin hits :orog2 for 9
:paladin hits :orog0 for 12
:cleric casts :spell-2 cure wound and heals :cleric from 0 to 13
:cleric has no remaining spell slots to heal with
remaining hp: 80</code></pre>
</details>
<details>
<summary>88 hp</summary>
<pre><code>simulation# 4
encounter# 0
:cleric blesses :fighter :paladin :cleric
round# 0
:sorcerer misses :orog0
:paladin hits :orog0 for 11
:paladin hits :orog0 for 13
:fighter hits :orog0 for 13
:fighter misses :orog0
:orog0 hits :paladin for 14
:orog0 misses :cleric
:orog1 hits :paladin for 10
:orog1 misses :paladin
:orog2 misses :paladin
:orog2 misses :paladin
:cleric hits :orog0 for 8
round# 1
:sorcerer misses :orog1
:paladin hits :orog1 for 11 #blessed
:paladin misses :orog1
:fighter misses :orog1
:fighter misses :orog1
:orog1 misses :sorcerer
:orog1 misses :cleric
:orog2 misses :paladin
:orog2 misses :sorcerer
:cleric misses :orog1
round# 2
:sorcerer hits :orog1 for 12
:paladin misses :orog1
:paladin hits :orog1 for 10
:fighter hits :orog1 for 8
:fighter misses :orog1
:orog1 misses :paladin
:orog1 hits :cleric for 14
:orog2 hits :paladin for 12
:orog2 hits :paladin for 11
:cleric casts :spell-2 cure wound and heals :paladin from 0 to 7
round# 3
:sorcerer misses :orog1
:paladin hits :orog1 for 13
:paladin hits :orog2 for 12 #blessed
:fighter hits :orog2 for 10
:fighter misses :orog2
:orog2 hits :sorcerer for 12
:orog2 crits :sorcerer for 13
:cleric hits :orog2 for 11 #blessed
round# 4
:sorcerer hits :orog2 for 2
:paladin hits :orog2 for 12
:paladin misses :orog0
:fighter misses :orog0
:fighter hits :orog0 for 9 #blessed
:cleric crits :orog0 for 11
:cleric has no remaining spell slots to heal with
remaining hp: 88</code></pre>
</details>
<details>
<summary>96 hp</summary>
<pre><code>simulation# 0
encounter# 0
:cleric blesses :fighter :paladin :cleric
round# 0
:fighter misses :orog0
:fighter hits :orog0 for 14
:sorcerer misses :orog0
:orog0 misses :sorcerer
:orog0 misses :paladin
:orog1 misses :fighter
:orog1 misses :fighter
:orog2 hits :cleric for 13
:orog2 misses :sorcerer
:cleric hits :orog0 for 5
:paladin hits :orog0 for 7
:paladin misses :orog0
round# 1
:fighter misses :orog0
:fighter hits :orog0 for 8
:sorcerer hits :orog0 for 11
:orog1 hits :paladin for 11
:orog1 misses :cleric
:orog2 crits :sorcerer for 14
:orog2 misses :cleric
:cleric hits :orog1 for 10 #blessed
:paladin misses :orog1
:paladin hits :orog1 for 12
round# 2
:fighter misses :orog1
:fighter crits :orog1 for 15
:sorcerer hits :orog1 for 13
:orog2 crits :cleric for 21
:orog2 crits :paladin for 19
:cleric crits :orog2 for 19
:paladin hits :orog2 for 11 #blessed
:paladin misses :orog2
round# 3
:fighter hits :orog2 for 11 #blessed
:fighter hits :orog0 for 7
:sorcerer crits :orog0 for 14
:cleric misses :orog0
:paladin misses :orog0
:paladin hits :orog0 for 13 #blessed
:cleric casts :spell-2 cure wound and heals :cleric from 4 to 14
:cleric has no remaining spell slots to heal with
remaining hp: 96</code></pre>
</details>
<details>
<summary>110 hp</summary>
<pre><code>simulation# 3
encounter# 0
:cleric blesses :fighter :paladin :cleric
round# 0
:cleric hits :orog0 for 5
:paladin hits :orog0 for 7
:paladin misses :orog0
:fighter hits :orog0 for 11
:fighter hits :orog0 for 9
:orog0 misses :cleric
:orog0 hits :fighter for 7
:orog1 hits :sorcerer for 13
:orog1 misses :paladin
:orog2 misses :paladin
:orog2 hits :fighter for 16
:sorcerer crits :orog0 for 27
round# 1
:cleric hits :orog1 for 9
:paladin hits :orog1 for 14 #blessed
:paladin hits :orog1 for 13 #blessed
:fighter hits :orog1 for 10
:fighter misses :orog2
:orog2 hits :fighter for 10
:orog2 hits :paladin for 12
:sorcerer hits :orog2 for 11
round# 2
:cleric hits :orog2 for 4
:paladin hits :orog2 for 12
:paladin misses :orog2
:fighter hits :orog2 for 9 #blessed
:fighter misses :orog2
:orog2 hits :sorcerer for 6
:orog2 misses :cleric
:sorcerer misses :orog2
round# 3
:cleric hits :orog2 for 4
:paladin hits :orog2 for 10
:paladin misses :orog0
:fighter misses :orog0
:fighter hits :orog0 for 13
:sorcerer misses :orog0
:cleric casts :spell-2 cure wound and heals :fighter from 11 to 21
:cleric has no remaining spell slots to heal with
remaining hp: 110</code></pre>
</details>
<details>
<summary>117 hp</summary>
<pre><code>simulation# 10
encounter# 0
:cleric blesses :fighter :paladin :cleric
round# 0
:fighter misses :orog0
:fighter hits :orog0 for 9 #blessed
:paladin hits :orog0 for 8
:paladin misses :orog0
:orog0 hits :paladin for 15
:orog0 misses :fighter
:orog1 misses :fighter
:orog1 misses :paladin
the sorcerer uses shield to block :orog2
:orog2 misses :sorcerer
:orog2 misses :cleric
:cleric misses :orog0
:sorcerer misses :orog0
round# 1
:fighter hits :orog0 for 10
:fighter misses :orog0
:paladin misses :orog0
:paladin hits :orog0 for 9
:orog0 misses :sorcerer
the sorcerer uses shield to block :orog0
:orog0 misses :sorcerer
:orog1 hits :paladin for 7
:orog1 crits :cleric for 20
:orog2 misses :sorcerer
:orog2 hits :fighter for 10
:cleric crits :orog0 for 17
:sorcerer misses :orog1
round# 2
:fighter hits :orog1 for 10
:fighter hits :orog1 for 12
:paladin hits :orog1 for 14 #blessed
:paladin hits :orog1 for 11
:orog2 hits :sorcerer for 6
:orog2 misses :fighter
:cleric misses :orog2
:sorcerer misses :orog2
round# 3
:fighter misses :orog2
:fighter misses :orog2
:paladin misses :orog2
:paladin crits :orog2 for 15
:orog2 misses :cleric
:orog2 misses :paladin
:cleric hits :orog2 for 5
:sorcerer hits :orog2 for 13
round# 4
:fighter hits :orog2 for 7
:fighter misses :orog0
:paladin hits :orog0 for 10
:paladin hits :orog0 for 7
:cleric misses :orog0
:sorcerer hits :orog0 for 9
:cleric casts :spell-2 cure wound and heals :paladin from 22 to 33
:cleric has no remaining spell slots to heal with
remaining hp: 117</code></pre>
</details>
<details>
<summary>117 hp</summary>
<pre><code>simulation# 5
encounter# 0
:cleric blesses :fighter :paladin :cleric
round# 0
:paladin hits :orog0 for 10
:paladin misses :orog0
:fighter crits :orog0 for 15
:fighter hits :orog0 for 8 #blessed
:cleric hits :orog0 for 10
:orog0 misses :paladin
:orog0 crits :paladin for 18
:orog1 misses :cleric
:orog1 hits :paladin for 8
:orog2 misses :paladin
:orog2 hits :fighter for 10
:sorcerer misses :orog0
round# 1
:paladin misses :orog0
:paladin misses :orog0
:fighter hits :orog0 for 12 #blessed
:fighter misses :orog1
:cleric hits :orog1 for 10
:orog1 hits :paladin for 7
:orog1 misses :fighter
:orog2 misses :sorcerer
:orog2 hits :paladin for 8
:sorcerer hits :orog1 for 16
round# 2
:paladin crits :orog1 for 21
:paladin misses :orog2
:fighter misses :orog2
:fighter hits :orog2 for 9
:cleric misses :orog2
:orog2 misses :cleric
:orog2 hits :cleric for 14
:sorcerer crits :orog2 for 17
round# 3
:paladin hits :orog2 for 8
:paladin hits :orog2 for 13 #blessed
:fighter hits :orog0 for 9
:fighter hits :orog0 for 13
:cleric hits :orog0 for 7 #blessed
:sorcerer hits :orog0 for 3
:cleric casts :spell-2 cure wound and heals :paladin from 3 to 21
:cleric has no remaining spell slots to heal with
remaining hp: 117</code></pre>
</details>
<details>
<summary>117 hp</summary>
<pre><code>simulation# 6
encounter# 0
:cleric blesses :fighter :paladin :cleric
round# 0
:orog0 hits :fighter for 10
:orog0 misses :fighter
:orog1 hits :paladin for 5
:orog1 hits :paladin for 13
:orog2 misses :paladin
:orog2 misses :cleric
:fighter hits :orog0 for 9
:fighter crits :orog0 for 15
:sorcerer hits :orog0 for 9
:cleric hits :orog0 for 11
:paladin misses :orog0
:paladin hits :orog0 for 8 #blessed
round# 1
:orog1 crits :cleric for 15
:orog1 misses :paladin
:orog2 misses :paladin
:orog2 misses :fighter
:fighter hits :orog1 for 8
:fighter misses :orog1
:sorcerer hits :orog1 for 13
:cleric hits :orog1 for 7
:paladin hits :orog1 for 7 #blessed
:paladin misses :orog1
round# 2
:orog1 misses :fighter
:orog1 misses :fighter
:orog2 hits :fighter for 11
:orog2 misses :fighter
:fighter misses :orog1
:fighter hits :orog1 for 7
:sorcerer misses :orog2
:cleric misses :orog2
:paladin hits :orog2 for 12 #blessed
:paladin misses :orog2
round# 3
:orog2 misses :fighter
:orog2 hits :cleric for 6
:fighter hits :orog2 for 14
:fighter hits :orog2 for 10 #blessed
:sorcerer misses :orog2
:cleric misses :orog2
:paladin misses :orog2
:paladin hits :orog2 for 13
:cleric casts :spell-2 cure wound and heals :fighter from 23 to 36
:cleric has no remaining spell slots to heal with
remaining hp: 117</code></pre>
</details>
<details>
<summary>123 hp</summary>
<pre><code>simulation# 8
encounter# 0
:cleric blesses :fighter :paladin :cleric
round# 0
:fighter hits :orog0 for 14
:fighter hits :orog0 for 7 #blessed
:orog0 misses :fighter
:orog0 misses :cleric
:orog1 misses :paladin
:orog1 misses :cleric
:orog2 misses :fighter
:orog2 misses :paladin
:sorcerer hits :orog0 for 16
:paladin hits :orog0 for 13
:paladin misses :orog1
:cleric misses :orog1
round# 1
:fighter misses :orog1
:fighter misses :orog1
:orog1 hits :sorcerer for 11
:orog1 hits :cleric for 11
:orog2 hits :sorcerer for 15
:orog2 crits :paladin for 13
:sorcerer hits :orog1 for 17
:paladin hits :orog1 for 7
:paladin hits :orog1 for 10
:cleric misses :orog1
round# 2
:fighter misses :orog1
:fighter hits :orog1 for 11
:orog2 hits :fighter for 8
:orog2 misses :cleric
:sorcerer hits :orog2 for 11
:paladin hits :orog2 for 11
:paladin misses :orog2
:cleric hits :orog2 for 10
round# 3
:fighter hits :orog2 for 7
:fighter hits :orog2 for 8 #blessed
:orog2 misses :paladin
:orog2 misses :sorcerer
:sorcerer hits :orog2 for 14
:paladin misses :orog0
:paladin hits :orog0 for 12
:cleric hits :orog0 for 6
:cleric casts :spell-2 cure wound and heals :sorcerer from 12 to 29
:cleric has no remaining spell slots to heal with
remaining hp: 123</code></pre>
</details>
<details>
<summary>128 hp</summary>
<pre><code>simulation# 7
encounter# 0
:cleric blesses :fighter :paladin :cleric
round# 0
:paladin misses :orog0
:paladin misses :orog0
:orog0 misses :cleric
:orog0 hits :sorcerer for 13
:orog1 misses :fighter
:orog1 misses :fighter
:orog2 misses :paladin
:orog2 hits :paladin for 10
:fighter hits :orog0 for 9 #blessed
:fighter hits :orog0 for 11 #blessed
:sorcerer hits :orog0 for 17
:cleric hits :orog0 for 4
round# 1
:paladin misses :orog0
:paladin hits :orog0 for 10
:orog1 misses :paladin
:orog1 misses :paladin
:orog2 misses :fighter
:orog2 misses :cleric
:fighter hits :orog1 for 13
:fighter hits :orog1 for 13
:sorcerer misses :orog1
:cleric hits :orog1 for 4
round# 2
:paladin hits :orog1 for 12 #blessed
:paladin hits :orog2 for 9
:orog2 hits :paladin for 15
:orog2 hits :paladin for 9
:fighter hits :orog2 for 10
:fighter misses :orog2
:sorcerer misses :orog2
:cleric misses :orog2
round# 3
:paladin hits :orog2 for 10 #blessed
:paladin hits :orog2 for 10
:orog2 misses :fighter
:orog2 misses :fighter
:fighter misses :orog2
:fighter misses :orog2
:sorcerer misses :orog2
:cleric misses :orog2
round# 4
:paladin hits :orog2 for 9
:paladin hits :orog0 for 8 #blessed
:fighter hits :orog0 for 8
:fighter misses :orog0
:sorcerer hits :orog0 for 8
:cleric hits :orog0 for 11
:cleric casts :spell-2 cure wound and heals :paladin from 10 to 21
:cleric has no remaining spell slots to heal with
remaining hp: 128</code></pre>
</details>
