# Snippet

```
# remove all redis key
redis-cli keys '*' | xargs redis-cli del
```