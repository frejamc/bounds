# Bounds

Calculates the block depth for Bitcoin and Ethereum such that the finality 
of a target transaction is violated with probability no greater than (or at least) the specified 
security value for a specified ratio of honest miners.  

The formulas used in the code are derived in the paper [Bitcoin's Latency–Security Analysis Made Simple](https://dl.acm.org/doi/abs/10.1145/3558535.3559791).
