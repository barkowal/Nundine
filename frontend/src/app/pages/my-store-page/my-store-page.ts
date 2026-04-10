import { Component, Type, viewChild, ViewContainerRef } from '@angular/core';
import { AddProductForm } from '../../components/add-product-form/add-product-form';

@Component({
  selector: 'app-my-store-page',
  imports: [],
  templateUrl: './my-store-page.html',
  styleUrl: './my-store-page.scss',
})
export class MyStorePage {

  protected optionContainer = viewChild('optionContainer', { read: ViewContainerRef });
  private optionMap: Record<string, Type<any>> = {
    "ADD": AddProductForm
  }

  showOption(mode: string) {
    const componentType = this.optionMap[mode];
    if (!componentType) return;

    this.optionContainer()?.clear();
    this.optionContainer()?.createComponent(componentType);
  }

}
