(defproject fourplay "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url         "http://example.com/FIXME"
  :license     {:name "Eclipse Public License"
                :url  "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.8.0" :scope "provided"]
                 [org.clojure/clojurescript "1.9.229" :scope "provided"]
                 [reagent "0.6.1"]]

  :plugins [[lein-cljsbuild "1.1.5"]
            [lein-figwheel "0.5.9"]]

  :min-lein-version "2.5.3"

  :clean-targets ^{:protect false} [:target-path
                                    [:cljsbuild :builds :app :compiler :output-dir]
                                    [:cljsbuild :builds :app :compiler :output-to]]

  :resource-paths ["public"]

  :figwheel {:http-server-root "."
             :nrepl-port       7888
             :nrepl-middleware ["cider.nrepl/cider-middleware"
                                "refactor-nrepl.middleware/wrap-refactor"
                                "cemerick.piggieback/wrap-cljs-repl"]
             :css-dirs         ["public/css"]}

  :less {:source-paths ["src/less/"]
         :target-path  "public/css"}

  :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}

  :cljsbuild {:builds {:dev {:source-paths ["src" "env/dev/cljs"]
                             :compiler     {:main          "fourplay.dev"
                                            :output-to     "public/js/app.js"
                                            :output-dir    "public/js/out"
                                            :asset-path    "js/out"
                                            :source-map    true
                                            :optimizations :none
                                            :pretty-print  true}
                             :figwheel     {:open-urls ["http://localhost:3449/index.html"]}}
                       
                       
                       :release {:source-paths ["src" "env/prod/cljs"]
                                 :compiler  {:output-to     "public/js/app.js"
                                             :output-dir    "public/js/release"
                                             :asset-path    "js/out"
                                             :optimizations :advanced
                                             :pretty-print  false}}}}
  
  :aliases {"release" ["do" "clean" ["cljsbuild" "once" "release"]]}

  :profiles {:dev {:dependencies [[figwheel-sidecar "0.5.9"]
                                  [org.clojure/tools.nrepl "0.2.12"]
                                  [com.cemerick/piggieback "0.2.1"]]
                   
                   :plugins      [[lein-less "1.7.5"]]}})
