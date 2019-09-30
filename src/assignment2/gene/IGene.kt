package assignment2.gene

import com.sun.corba.se.impl.io.TypeMismatchException

/**
 * Genes are the basic elements of the `assignment 2` package.
 * They contain the actual information of the individuals of a population.
 * In this context, the information contained in a gene is called DNA.
 *
 * @author  Bastián Inostroza
 */
interface IGene<out DNA> {
    val dna: DNA

    /**
     * Copy the gene into another.
     *
     * @param other Gene that's going to be overwritten with this one.
     */
    fun copyTo(other: IGene<*>)

    abstract fun copyFromCharGene(other: CharGene)

}