#!/bin/bash

set -e

if [[ "$EAS_BUILD_PLATFORM" == "ios" ]]; then
  HOMEBREW_NO_AUTO_UPDATE=1 brew install homebrew/cask-versions/temurin11
fi

exec npx shadow-cljs release app
