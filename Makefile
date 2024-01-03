.DEFAULT_GOAL := run_default

run_default:
	./gradlew run --args="true thales.ee.duth.gr 4475 0 50 123"

official:
	./gradlew run --args="true thales.ee.duth.gr 4475"

.PHONY: run_default official
