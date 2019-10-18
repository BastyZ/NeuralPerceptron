package assignment2.gene

import assignment2.Box
import kotlin.random.Random.Default.nextInt

class BoxGene: IGene<Box> {
    override fun copyFromBoxGene(other: BoxGene) {
        this.dna = other.dna
        this.boxes = other.boxes
        this.isValid = other.isValid
    }

    override fun copyFromCharGene(other: CharGene) {

    }

    private var boxes: Array<Box>
        private set

    override var dna: Box
        private set

    /** A character is valid if it's contained in the gene alphabet. */
    var isValid: Boolean
        private set

    constructor(possibleBoxes: Array<Box>) {
        boxes = possibleBoxes
        dna = boxes[nextInt(boxes.size)]
        isValid = true
    }

    constructor(box: Box, possibleBoxes: Array<Box>) {
        boxes = possibleBoxes
        dna = box
        isValid = boxes.asSequence().contains(dna)
    }

    /**
     * Returns a copy of this gene.
     */
    fun copy() = BoxGene(dna, boxes)

    override fun copyTo(other: IGene<*>) {
        other.copyFromBoxGene(this)
    }

    /**
     * Checks if this gene is equal to another.
     *
     * @param other
     *    Gene to be checked.
     * @return
     *    `true` if the two genes are equal, `false` if not.
     */
    override fun equals(other: Any?): Boolean {
        return other is BoxGene
                && this.dna == other.dna
                && this.boxes.contentEquals(other.boxes)
    }

    /**
     * Returns a hash code value for the object.
     */
    override fun hashCode(): Int {
        var result = boxes.hashCode()
        result = 31 * result + dna.hashCode()
        result = 31 * result + isValid.hashCode()
        return result
    }

    /**
     * Returns a string representation of this gene.
     */
    override fun toString() = dna.toString()

    fun replaceWith(value: Box) {
        dna = value
    }
}
