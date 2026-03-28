impl Solution {
    pub fn find_the_string(lcp: Vec<Vec<i32>>) -> String {
        let n = lcp.len();
        let mut word = vec!['\0'; n];
        let mut current = b'a';

        for i in 0..n {
            if word[i] == '\0' {
                if current > b'z' {
                    return String::new();
                }
                word[i] = current as char;
                for j in i + 1..n {
                    if lcp[i][j] > 0 {
                        word[j] = word[i];
                    }
                }
                current += 1;
            }
        }

        for i in (0..n).rev() {
            for j in (0..n).rev() {
                if word[i] != word[j] {
                    if lcp[i][j] != 0 {
                        return String::new();
                    }
                } else {
                    if i == n - 1 || j == n - 1 {
                        if lcp[i][j] != 1 {
                            return String::new();
                        }
                    } else {
                        if lcp[i][j] != lcp[i + 1][j + 1] + 1 {
                            return String::new();
                        }
                    }
                }
            }
        }

        word.iter().collect()
    }
}