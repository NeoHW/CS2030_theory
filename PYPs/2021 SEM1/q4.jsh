(a)
THE QUICK BROWN FOX

(b)
APPLP

(c)
// Functor law 1 : Identity law
// any functor mapped with an identity will give back the functor

Box.of(A).map(x -> x).toString() produces A

// Functor law 2: Associative law
// functor.map(f.andThen(g)) ≡ functor.map(f).map(g)

Box.of(APPLP).map(s-> s.replace(’P’, ’E’)).map(s-> s.replace(’E’, ’P’))
= APPLP
= Box.of(APPLP).map(s-> s.replace(’P’, ’E’).andThen(s-> s.replace(’E’, ’P’)))