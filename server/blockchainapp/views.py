from django.shortcuts import render
from rest_framework.views import APIView
from rest_framework.response import Response
from blockchain.BlockChain import BlockChain

# Create your views here.
class BlockChain(APIView):

    blockchain = Blockchain()

    def mine_block():
    previous_block = blockchain.get_previous_block()
    previous_proof = previous_block['proof']
    proof = blockchain.proof_of_work(previous_proof)
    previous_hash = blockchain.hash(previous_block)
    block = blockchain.create_block(proof, previous_hash)
    responses = {
        'message' : 'Congratulations, you just mined a block!',
        **block
    }
    return Response(response)

    def get_chain():
        response = {
            'chain': blockchain.chain,
            'length' : len(blockchain.chain)
        }
        return Response(data = {'chain': blockchain.chain,'length' : len(blockchain.chain)}, status = status.HTTP_200_OK)
