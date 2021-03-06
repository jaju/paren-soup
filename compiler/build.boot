(set-env!
  :source-paths #{"src"}
  :dependencies '[[adzerk/boot-cljs "1.7.228-2" :scope "test"]
                  ; project deps
                  [org.clojure/clojurescript "1.9.660"]
                  [eval-soup "1.2.2"]])

(require
  '[adzerk.boot-cljs :refer [cljs]]
  '[clojure.java.io :as io])

(task-options!
  cljs {:compiler-options {:static-fns true
                           :optimize-constants true}})

(deftask build []
  (set-env! :source-paths #{"src"})
  (comp
    (cljs :optimizations :simple)
    (target)
    (with-pass-thru _
      (let [from (io/file "target/main.js")
            to (io/file "../resources/public/paren-soup-compiler.js")]
        (.renameTo from to)))))

