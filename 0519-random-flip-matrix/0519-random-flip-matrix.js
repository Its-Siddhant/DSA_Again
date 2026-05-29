class Solution {
    constructor(m, n) {
        this.m = m;
        this.n = n;
        this.total = m * n;
        this.map = new Map();
    }

    flip() {
        const r = Math.floor(Math.random() * this.total);
        this.total--;

        const idx = this.map.has(r) ? this.map.get(r) : r;
        this.map.set(r, this.map.has(this.total) ? this.map.get(this.total) : this.total);

        return [Math.floor(idx / this.n), idx % this.n];
    }

    reset() {
        this.total = this.m * this.n;
        this.map.clear();
    }
}