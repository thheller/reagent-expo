```
$ yarn
$ shadow-cljs watch app
;; wait for first compile to finish or expo gets confused
$ yarn start

;; production build
$ shadow-cljs release app
;; no idea what to do for expo
```

You can also use `expo start --web` in order to run [react native web](https://github.com/necolas/react-native-web).

You'll want to disable hot reload for react native web (since shadow-cljs is a lot faster, and you'll lose all state). It's not possible to do this "properly", but neupsh@clojurians suggested the following:

> you can't turn off the live reload, but you can workaround it

> you can block requests to /sockjs-node/* in chrome (didn't work on firefox)

1. Open Developer Tools
2. Click the three dots
3. More tools > Request blocking
4. Click the `+`
5. Enter `*/sockjs-node/*`
6. Done!

![Step 1-3](https://memset.se/9429/e0f0c065c9c0231d80681ca7da72bbcd4a67ff1e)

![Step 4-6](https://memset.se/9430/4bd73bd45cda2f096a2d2106d22ba8130b0c5bd2)


## Tests

You can find an example of using `jest` to test `react-native` apps here.

- https://github.com/mynomoto/reagent-expo/tree/jest-test

## Notes

The `:app` build will create an `app/index.js`. In `release` mode that is the only file needed. In dev mode the `app` directory will contain many more `.js` files.

`:init-fn` is called after all files are loaded and in the case of `expo` must render something synchronously as it will otherwise complain about a missing root component. The `shadow.expo/render-root` takes care of registration and setup.

You should disable the `expo` live reload stuff and let `shadow-cljs` handle that instead as they will otherwise interfere with each other.

Source maps don't seem to work properly. `metro` propably doesn't read input source maps when converting sources as things are correctly mapped to the source .js files but not their sources.

Initial load in dev is quite slow since `metro` processes the generated `.js` files.

`reagent.core` loads `reagent.dom` which will load `react-dom` which we don't have or need. Including the `src/main/reagent/dom.cljs` to create an empty shell. Copied from [re-natal](https://github.com/drapanjanas/re-natal/blob/master/resources/cljs-reagent6/reagent_dom.cljs).
