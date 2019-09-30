(ns example.app-test
  (:require
    [example.app :as app]
    ["react-test-renderer" :as renderer]
    [reagent.core :as r]))

(js/test
  "Adds 1 + 2 to equal 3"
  #(.. (js/expect (+ 1 2)) (toBe 3)))

(js/it
  "Render correctly detaills component"
  (fn []
    (let [tree   (.. renderer (create (r/as-element [app/root])) (toJSON))]
        (.. (js/expect tree) (toMatchSnapshot)))))
