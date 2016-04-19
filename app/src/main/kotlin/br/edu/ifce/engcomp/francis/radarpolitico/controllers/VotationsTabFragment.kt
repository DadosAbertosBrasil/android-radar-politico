package br.edu.ifce.engcomp.francis.radarpolitico.controllers


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.edu.ifce.engcomp.francis.radarpolitico.R
import br.edu.ifce.engcomp.francis.radarpolitico.miscellaneous.adapters.VotacoesRecyclerViewAdapter
import br.edu.ifce.engcomp.francis.radarpolitico.models.Votacao
import br.edu.ifce.engcomp.francis.radarpolitico.models.Voto
import java.util.*

class VotationsTabFragment : Fragment() {
    lateinit var adapter: VotacoesRecyclerViewAdapter
    lateinit var votacoesRecyclerView: RecyclerView

    internal var datasource: HashMap<Votacao, ArrayList<Voto>>

    init {
        datasource = HashMap<Votacao, ArrayList<Voto>>()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_votacoes, container, false)

        this.votacoesRecyclerView = rootView.findViewById(R.id.votacoesRecyclerView) as RecyclerView
        this.initRecyclerView()

        return rootView
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(activity)

        this.adapter = VotacoesRecyclerViewAdapter(this.datasource)

        this.votacoesRecyclerView.setHasFixedSize(false)
        this.votacoesRecyclerView.layoutManager = layoutManager
        this.votacoesRecyclerView.adapter = adapter
        this.votacoesRecyclerView.itemAnimator = DefaultItemAnimator()
    }
}
