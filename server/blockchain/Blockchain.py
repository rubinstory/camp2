import datetime
import hashlib
import json

class Blockchain:
    
    def __init__(self):
        self.chain = []
        self.create_block(proof = 1, previous_hash = '0') #genesis block

    def create_block(self, proof, previous_hash): #create block structured by json
        block = {
            'index': len(self.chain)+1,
            'timestamp': str(datetime.datetime.now()),
            'proof': proof,
            'previous_hash': previous_hash
        }
        self.chain.append(block)
        return block

    def get_previous_block(self): #return last block
        return self.chain[-1]

    def proof_of_work(self, previous_proof):#get previous proof, and return new proof that satisfy specific condition
        new_proof = 1
        check_proof = False

        while check_proof is False:
            hash_operation = hashlib.sha256(
                str(new_proof**2-previous_proof**2).encode())
                .hexdigest()
            
            if hash_operation.startswith('0000'):
                check_proof = True
            else:
                new_proof +=1
        return new_proof

    def hash(self, block):
        encoded_block = json.dumps(bloc, sort_keys = True).encode()
        return hashlib.sha256(encoded_block).hexdigest()

    def is_valid_chain(self, chain):
        previous_block = chain[0]
        block_index = 1

        while block_index < len(chain):
            block = chain[block_index]
            if block['previous_hash'] ! = self.hash(previous_block):
                return False

            previous_proof = previous_block['proof']
            proof = block['proof']
            hash_operation = hashlib.sha256(str(proof**2-previous_proof**2).encode()).hexdigest()

            if not hash_operation.startswith('0000'):
                return False

            previous_block = block
            block_index += 1

        return True




# def mine_block():
#     previous_block = blockchain.get_previous_block()
#     previous_proof = previous_block['proof']
#     proof = blockchain.proof_of_work(previous_proof)
#     previous_hash = blockchain.hash(previous_block)
#     block = blockchain.create_block(proof, previous_hash)
#     responses = {
#         'message' : 'Congratulations, you just mined a block!',
#         **block
#     }
#     return jsonify(responses)

# def get_chain():
#     response = {
#         'chain': blockchain.chain,
#         'length' : len(blockchain.chain)
#     }
#     return jsonify(response), 200