package assignment2

import kotlin.random.Random.Default.nextInt

class Population(size: Int, beingFactory: BeingFactory) {

    /**
     * List of the beings.
     */
    private var being = mutableListOf<Being>()

    init {
        for (i in 0 until size) {
            val ind = beingFactory.build()
            being.add(ind)
        }
        being.sort()
    }

    /**
     * Evolves the population to a next generation.
     */
    fun evolve() {
        val children = mutableListOf<Being>()
        val survivors = being.size / 4
        var i = 0
        while (children.size < being.size)
            if (i < being.size - survivors - 1) {
                val parent1 = tournamentSelection()
                val parent2 = tournamentSelection()
                val mixingPoint = nextInt(parent1.size)

                val child = parent1.crossover(parent2, mixingPoint)
                child.mutate()
                children.add(child)

                val otherChild = parent2.crossover(parent1, mixingPoint)
                otherChild.mutate()
                children.add(otherChild)

                i += 2
            } else
                children.add(being[i++])
        being = children
        being.sort()
    }

    /** Returns the fittest individual. */
    fun getFittest() = being.last()

    /** Selects a random individual prioritizing the ones with greater fitness. */
    private fun tournamentSelection(): Being {
        val index = maxOf(nextInt(being.size), nextInt(being.size))
        // higher index means higher fitness.
        return being[index]
    }
}
