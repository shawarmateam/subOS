# subOS

SubOs is an "OS in OS". writed on Java

# ImgV
in .imgv all strings after 1st string is comments.

- \d - dir open (iteration) (0xFF)
- \c - dir close (0xFE)
- \f - file data starts (0xFD)
- \e - file data ends (0xFC)

For example how this work:
```imgv
/ {
    bin {
        adisteyf_pswrd
    }
    
    users {
        adisteyf {
            0123.msg
        }
    
        root {
        }
    }
}
```