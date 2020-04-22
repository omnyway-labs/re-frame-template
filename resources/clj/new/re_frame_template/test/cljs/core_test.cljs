(ns {{namespace}}.core-test
    (:require [cljs.test :refer-macros [deftest testing is]]
              [{{namespace}}.core :as core]))

(deftest fake-test
  (testing "fake description"
    (is (= 1 2))))
