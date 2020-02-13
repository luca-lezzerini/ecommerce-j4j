import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Visualizzatore08Component } from './visualizzatore08.component';

describe('Visualizzatore08Component', () => {
  let component: Visualizzatore08Component;
  let fixture: ComponentFixture<Visualizzatore08Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Visualizzatore08Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Visualizzatore08Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
